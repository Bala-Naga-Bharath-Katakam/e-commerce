package com.ecommerce.products.service;

import com.ecommerce.products.entity.Product;
import com.ecommerce.products.entity.ProductDTO;
import com.ecommerce.products.exceptions.APIException;
import com.ecommerce.products.exceptions.ResourceNotFoundException;
import com.ecommerce.products.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing products.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    /**
     * Adds a new product to the database.
     *
     * @param productDTO the product data transfer object
     * @return ResponseEntity containing the saved ProductDTO
     */
    public Mono<ResponseEntity<ProductDTO>> addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setImage("default.png");
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
        product.setSpecialPrice(specialPrice);

        product.setProductName(productDTO.getProductName());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        System.out.println("product "+product);
        return productRepository.save(product)
                .map(savedProduct -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(modelMapper.map(savedProduct, ProductDTO.class)))
                .switchIfEmpty(Mono.error(new APIException("Product already exists!")));
    }

    /**
     * Retrieves all products from the database.
     *
     * @return ResponseEntity containing the list of ProductDTOs
     */
    public Mono<ResponseEntity<List<ProductDTO>>> getAllProducts() {
        return productRepository.findAll()
                .collectList()
                .map(products -> ResponseEntity.ok(products.stream()
                        .map(product -> modelMapper.map(product, ProductDTO.class))
                        .collect(Collectors.toList())));
    }

    /**
     * Updates an existing product.
     *
     * @param productId  the ID of the product to be updated
     * @param productDTO the updated product data transfer object
     * @return ResponseEntity containing the updated ProductDTO
     */
    public Mono<ResponseEntity<ProductDTO>> updateProduct(Long productId, ProductDTO productDTO) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product", "productId", productId)))
                .flatMap(existingProduct -> {
                    existingProduct.setProductName(productDTO.getProductName());
                    existingProduct.setDescription(productDTO.getDescription());
                    existingProduct.setQuantity(productDTO.getQuantity());
                    existingProduct.setDiscount(productDTO.getDiscount());
                    existingProduct.setPrice(productDTO.getPrice());
                    existingProduct.setSpecialPrice(productDTO.getSpecialPrice());

                    return productRepository.save(existingProduct)
                            .map(savedProduct -> ResponseEntity.ok(modelMapper.map(savedProduct, ProductDTO.class)));
                });
    }

    /**
     * Deletes a product from the database.
     *
     * @param productId the ID of the product to be deleted
     * @return ResponseEntity containing the deleted ProductDTO
     */
    public Mono<ResponseEntity<ProductDTO>> deleteProduct(Long productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product", "productId", productId)))
                .flatMap(product -> productRepository.delete(product)
                        .then(Mono.just(ResponseEntity.ok(modelMapper.map(product, ProductDTO.class)))));
    }

    /**
     * Updates the image of an existing product.
     *
     * @param productId the ID of the product
     * @param image     the new image file
     * @return ResponseEntity containing the updated ProductDTO
     * @throws IOException if there is an error while uploading the image
     */
    public Mono<ResponseEntity<ProductDTO>> updateProductImage(Long productId, MultipartFile image) throws IOException {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product", "productId", productId)))
                .flatMap(existingProduct -> {
                    String fileName = null;
                    try {
                        fileName = fileService.uploadImage(path, image);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    existingProduct.setImage(fileName);

                    return productRepository.save(existingProduct)
                            .map(savedProduct -> ResponseEntity.ok(modelMapper.map(savedProduct, ProductDTO.class)));
                });
    }
}
