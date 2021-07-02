package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.EditorPageStateKeeper;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author S.K.C.Reijntjes
 *
 * This controller arranges all views and actions for the editor page
 */

@Controller
@SessionAttributes("editorPageStateKeeper")
public class EditorPageController {

    public final CategoryService categoryService;
    public final ProductService productService;

    @Autowired
    public EditorPageController(CategoryService categories, ProductService products) {
        this.categoryService = categories;
        this.productService = products;
    }

    @ModelAttribute("editorPageStateKeeper")
    public EditorPageStateKeeper editorPageStateKeeper() {
        return new EditorPageStateKeeper();
    }

    @GetMapping({"/editor"})
    protected String showEditorPage(
            Model model,
            @ModelAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        model.addAttribute("allCategories", categoryService.getAll());
        if (editorPageStateKeeper.getCurrentCategory() != null &&
                editorPageStateKeeper.getCurrentCategory().getCategoryCode() > 0) {
            model.addAttribute("allProductsByCategory",
                    productService.getProductsByCategory(editorPageStateKeeper.getCurrentCategory().getCategoryCode()));
        }
        model.addAttribute("categoryFormState", editorPageStateKeeper.isShowCategoryForm());
        model.addAttribute("productFormState", editorPageStateKeeper.isShowProductForm());

        setupCategory(editorPageStateKeeper);
        setupProduct(editorPageStateKeeper);

        model.addAttribute("category", editorPageStateKeeper.getCurrentCategory());
        model.addAttribute("product", editorPageStateKeeper.getCurrentProduct());

        model.addAttribute("selectedCategory", editorPageStateKeeper.getCurrentCategory().getCategoryCode());
        model.addAttribute("selectedProduct", editorPageStateKeeper.getCurrentProduct().getProductCode());

        model.addAttribute("selectedPage", "editorPage");
        model.addAttribute("clearedToDelete", isClearToDelete(editorPageStateKeeper));

        return "editor";
    }

    @GetMapping({"/editor/selectCategory/{categoryCode}"})
    protected String showProductsByCategory(
            @PathVariable("categoryCode") String categoryCodeString,
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        editorPageStateKeeper.setCurrentCategory(categoryService.findDtoByCode(Integer.parseInt(categoryCodeString)));

        return "redirect:/editor";
    }

    @GetMapping("/editor/addNew/category")
    protected String addNewCategory(
            Model model,
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {
        model.addAttribute("categoryFormState", editorPageStateKeeper.isShowCategoryForm());

        clearForm(editorPageStateKeeper);
        editorPageStateKeeper.clearCurrentCategory();

        if (!editorPageStateKeeper.isShowCategoryForm()) {
            editorPageStateKeeper.setShowCategoryForm(true);
        }

        return "redirect:/editor";
    }

    @PostMapping({"/editor/addNew/category"})
    protected String saveCategory(
            @ModelAttribute("category") CategoryDto categoryDto,
            BindingResult result,
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        if (!result.hasErrors()) {
            editorPageStateKeeper.setCurrentCategory(categoryDto);

            categoryService.saveCategory(editorPageStateKeeper.getCurrentCategory());

            // save category of added product to statekeeper, so you get to see the product immediately in its category
            editorPageStateKeeper.setCurrentCategory(categoryDto);

            editorPageStateKeeper.clearCurrentCategory();
            editorPageStateKeeper.setShowCategoryForm(false);
        }

        return "redirect:/editor";
    }

    @GetMapping("/editor/addNew/product")
    protected String addNewProduct(
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        clearForm(editorPageStateKeeper);
        editorPageStateKeeper.clearCurrentProduct();
        editorPageStateKeeper.clearCurrentCategory();

        showProductForm(editorPageStateKeeper);

        return "redirect:/editor";
    }

    @PostMapping("editor/addNew/product")
    protected String saveNewProduct(
            @ModelAttribute("product") ProductDto productDto,
            BindingResult result,
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        if (!result.hasErrors()) {
            editorPageStateKeeper.setCurrentProduct(productDto);

            productService.saveProduct(editorPageStateKeeper.getCurrentProduct());

            showCategoryForChangedProduct(productDto.getCategoryCode(), editorPageStateKeeper);

            clearForm(editorPageStateKeeper);
//            editorPageStateKeeper.clearCurrentCategory();
        }

        return "redirect:/editor";
    }

    @GetMapping({"/editor/category/{categoryCode}"})
    protected String editCategory(
            @PathVariable("categoryCode") String categoryCodeString,
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        clearForm(editorPageStateKeeper);
        CategoryDto selectedCategory = categoryService.findDtoByCode(Integer.parseInt(categoryCodeString));
        editorPageStateKeeper.setCurrentCategory(selectedCategory);
        editorPageStateKeeper.setCurrentCategory(categoryService.findDtoByCode(Integer.parseInt(categoryCodeString)));

        showCategoryForm(editorPageStateKeeper);

        return "redirect:/editor";
    }

    @PostMapping({"/editor/edit/category"})
    protected String saveExistingCategory(
            @ModelAttribute("category") CategoryDto categoryDto,
            BindingResult result,
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        if (!result.hasErrors()) {
            editorPageStateKeeper.setShowCategoryForm(true);
            editorPageStateKeeper.setCurrentCategory(categoryDto);

            categoryService.saveCategory(editorPageStateKeeper.getCurrentCategory());

            clearForm(editorPageStateKeeper);
        }

        return "redirect:/editor";
    }

    @GetMapping({"/editor/product/{productCode}"})
    protected String editProduct(
            @PathVariable("productCode") String productCodeString,
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        clearForm(editorPageStateKeeper);
        ProductDto selectedProduct = productService.findDtoByCode(Integer.parseInt(productCodeString));
        editorPageStateKeeper.setCurrentProduct(selectedProduct);

        showProductForm(editorPageStateKeeper);

        return "redirect:/editor";
    }

    @PostMapping("/editor/edit/product")
    protected String saveExistingProduct(
            @ModelAttribute("product") ProductDto productDto,
            BindingResult result,
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        if (!result.hasErrors()) {
            editorPageStateKeeper.setCurrentProduct(productDto);

            productService.saveProduct(editorPageStateKeeper.getCurrentProduct());

            showCategoryForChangedProduct(productDto.getCategoryCode(), editorPageStateKeeper);

            clearForm(editorPageStateKeeper);
        }

        return "redirect:/editor";
    }

    @GetMapping("/editor/delete/category")
    protected String deleteCategory(
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        categoryService.deleteCategory(editorPageStateKeeper.getCurrentCategory());

        clearForm(editorPageStateKeeper);
        editorPageStateKeeper.clearCurrentCategory();

        return "redirect:/editor";
    }

    @GetMapping("/editor/delete/product")
    protected String deleteProduct(@SessionAttribute(
            "editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {
        productService.deleteProduct(editorPageStateKeeper.getCurrentProduct());

        showCategoryForChangedProduct(editorPageStateKeeper.getCurrentProduct().getCategoryCode(),
                                      editorPageStateKeeper);

        clearForm(editorPageStateKeeper);

        return "redirect:/editor";
    }

    @GetMapping("/editor/cancel")
    protected String cancelForm(
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        clearForm(editorPageStateKeeper);

        return "redirect:/editor";
    }

    private void showCategoryForm(@SessionAttribute(
            "editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        if (!editorPageStateKeeper.isShowCategoryForm()) {
            editorPageStateKeeper.setShowCategoryForm(true);
        }
    }

    private void showProductForm(@SessionAttribute(
            "editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {
        if (!editorPageStateKeeper.isShowProductForm()) {
            editorPageStateKeeper.setShowProductForm(true);
        }
    }

    private void showCategoryForChangedProduct(
            Integer categoryCode,
            @SessionAttribute("editorPageStateKeeper") EditorPageStateKeeper editorPageStateKeeper) {

        // save category of added product to stateKeeper, so you get to see the product immediately in its category
        editorPageStateKeeper.setCurrentCategory(categoryService.findDtoByCode(categoryCode));
    }

    private void clearForm(EditorPageStateKeeper editorPageStateKeeper) {
        editorPageStateKeeper.clearCurrentProduct();
        editorPageStateKeeper.setShowCategoryForm(false);
        editorPageStateKeeper.setShowProductForm(false);
    }

    private boolean isClearToDelete(EditorPageStateKeeper editorPageStateKeeper) {
        boolean clearedToDelete = true;

        if (editorPageStateKeeper.getCurrentCategory().getProducts() != null) {
            if (!editorPageStateKeeper.getCurrentCategory().getProducts().isEmpty()) {
                clearedToDelete = false;
            }
        }

        return clearedToDelete;
    }

    private void setupCategory(EditorPageStateKeeper editorPageStateKeeper) {
        if (editorPageStateKeeper.getCurrentCategory() == null) {
            editorPageStateKeeper.setCurrentCategory(new CategoryDto());
        }
    }

    private void setupProduct(EditorPageStateKeeper editorPageStateKeeper) {
        if (editorPageStateKeeper.getCurrentProduct() == null) {
            editorPageStateKeeper.setCurrentProduct(new ProductDto());
        }
    }

}
