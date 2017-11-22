package service;

import dao.CategoryDao;
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

    public Category getCategoryByName() {

        return null;
    }

    public Category getCategoryById(Long id) {
        CategoryDao categoryDao = CategoryDao.newInstance();
        return categoryDao.get(id);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        List<Product> products = new ArrayList<>();
        CategoryDao categoryDao = CategoryDao.newInstance();
        Category category = categoryDao.get(categoryId);

        if (category != null) {

        }


        return null;
    }

    public List<Product> getAllProducts() {

        return null;
    }

    public Product getProductByName() {

        return null;
    }


    public Product getProductById(Long id) {

        return null;
    }

    public Product addNewProduct() {

        return null;
    }

    public Product updateProduct() {

        return null;
    }


}