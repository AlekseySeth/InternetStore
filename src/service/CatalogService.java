package service;

import dao.CategoryDao;
import dao.ProductDao;
import entity.product.Category;
import entity.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a.shestovsky
 */
public class CatalogService {

    private static CatalogService INSTANCE;

    private CatalogService() {
    }

    public static CatalogService newInstance() {
        if (INSTANCE == null) {
            synchronized (CatalogService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CatalogService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Category> getParentCategories() {
        return CategoryDao.newInstance().getParentCategories();
    }

    public List<Category> getCategoriesByParentId(Long parentId) {
        CategoryDao categoryDao = CategoryDao.newInstance();
        Category parent = categoryDao.get(parentId);
        return categoryDao.getCategoriesByParent(parent);
    }

    public Category addNewCategory(Category category) {
        CategoryDao categoryDao = CategoryDao.newInstance();
        return categoryDao.save(category);
    }

    public Category getCategoryById(Long id) {
        CategoryDao categoryDao = CategoryDao.newInstance();
        return categoryDao.get(id);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        List<Product> products = new ArrayList<>();
        ProductDao productDao = ProductDao.newInstance();
        CategoryDao categoryDao = CategoryDao.newInstance();
        Category category = categoryDao.get(categoryId);

        if (category.getCategory() == null) {
            List<Category> categoriesByParent = categoryDao.getCategoriesByParent(category);
            for (Category current : categoriesByParent) {
                products.addAll(productDao.getProductsByCategory(current));
            }
        } else {
            return productDao.getProductsByCategory(category);
        }
        return products;
    }

    public List<Product> getAllProducts() {
        return ProductDao.newInstance().getAll();
    }

    public Product getProductByName() {

        return null;
    }


    public Product getProductById(Long id) {
        return ProductDao.newInstance().get(id);
    }

    public Product addNewProduct(Product product) {
        return ProductDao.newInstance().save(product);
    }

    public boolean updateProduct(Product product) {
        return ProductDao.newInstance().update(product);
    }


}