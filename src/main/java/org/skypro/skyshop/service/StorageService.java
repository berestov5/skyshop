package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        initializeMapProductAndArticle();
    }

    private void initializeMapProductAndArticle() {
        addProductStorage(new SimpleProduct("Хлеб", 50, UUID.randomUUID()));
        addProductStorage(new DiscountedProduct("Колбаса", 250, 15, UUID.randomUUID()));
        addProductStorage(new FixPriceProduct("Хлеб", UUID.randomUUID()));

        addArticleStorage(new Article("Хлеб всему голова!!",
                "Урожайность пшеницы за полярным кругом бьет рекорды!", UUID.randomUUID()));
        addArticleStorage(new Article("Молочный рай.", "Доярки села Кукуева рапортуют...", UUID.randomUUID()));
        addArticleStorage(new Article("Космический кефир!",
                "Китай вывел спутник на орбиту земли используя двигатели на кефире.", UUID.randomUUID()));
    }

    private void addProductStorage(Product product) {
        productStorage.put(product.getId(), product);
    }

    private void addArticleStorage(Article article) {
        articleStorage.put(article.getId(), article);
    }

    public List<Product> getAllProduct() {
        return new ArrayList<>(Collections.unmodifiableCollection(productStorage.values()));
    }

    public List<Article> getAllArticle() {
        return new ArrayList<>(Collections.unmodifiableCollection(articleStorage.values()));
    }

    public List<Searchable> getAll() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(productStorage.values());
        result.addAll(articleStorage.values());
        return result;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productStorage.get(id));
    }
}
