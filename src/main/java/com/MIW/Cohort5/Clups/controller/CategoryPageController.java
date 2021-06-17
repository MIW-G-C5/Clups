package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.CategoryPageStateKeeper;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.ProductPageStateKeeper;
import com.MIW.Cohort5.Clups.services.CategoryService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Petrina IJnsen
 * Opdracht:
 * <p>
 * This controller arranges all views and actions for the Category page
 */

@Controller
@SessionAttributes("categoryPageStateKeeper")
public class CategoryPageController {

    public final CategoryService categoryService;

    @Autowired
    public CategoryPageController(CategoryService categories) {
        this.categoryService = categories;
    }

    @ModelAttribute("categoryPageStateKeeper")
    public CategoryPageStateKeeper categoryPageStateKeeper() {
        return new CategoryPageStateKeeper();
    }

    @GetMapping({"/categories"})
    protected String showCategoryPage(Model model,
                                      @ModelAttribute("categoryPageStateKeeper") CategoryPageStateKeeper categoryPageStateKeeper) {
        model.addAttribute("allCategories", categoryService.getAll());
        model.addAttribute("formCatState", categoryPageStateKeeper.isShowCatForm());

        if (categoryPageStateKeeper.getCurrentCategory() == null) {
            categoryPageStateKeeper.setCurrentCategory(new CategoryDto());
        }

        model.addAttribute("category", categoryPageStateKeeper.getCurrentCategory());
        model.addAttribute("selectedPage", "categoryPage");
        return "categoryEditor";
    }

    @GetMapping({"/categories/selectCategory/{categoryName}"})
    protected String selectCategory(
            @PathVariable("categoryName") String categoryName,
            @SessionAttribute("categoryPageStateKeeper") CategoryPageStateKeeper categoryPageStateKeeper) {
        categoryPageStateKeeper.setCategoryName(categoryName);
        showCatForm(categoryPageStateKeeper);

        return "redirect:/categories";
    }

    private void showCatForm(@SessionAttribute("categoryPageStateKeeper") CategoryPageStateKeeper categoryPageStateKeeper) {
        if (!categoryPageStateKeeper.isShowCatForm()) {
            categoryPageStateKeeper.setShowCatForm(true);
        }
    }

    private void clearCatForm(CategoryPageStateKeeper categoryPageStateKeeper){
        categoryPageStateKeeper.clearCurrentCategory();
        categoryPageStateKeeper.setShowCatForm(false);
    }

    @GetMapping({"/categories/addNew"})
    protected String addNewCategory
            (Model model, @SessionAttribute("categoryPageStateKeeper") CategoryPageStateKeeper categoryPageStateKeeper) {
        model.addAttribute("allCategoryNames", categoryService.getAll());
        model.addAttribute("formCatState", categoryPageStateKeeper.isShowCatForm());

        categoryPageStateKeeper.clearCurrentCategory();

        if (!categoryPageStateKeeper.isShowCatForm()) {
            categoryPageStateKeeper.setShowCatForm(true);
        }

        return "redirect:/categories";
    }

    @PostMapping({"/categories/addNew"})
    protected String saveCategory
            (@ModelAttribute("category") CategoryDto categoryDto, BindingResult result,
             @SessionAttribute("categoryPageStateKeeper") CategoryPageStateKeeper categoryPageStatekeeper) {

        if (!result.hasErrors()) {
            categoryPageStatekeeper.setCurrentCategory(categoryDto);

            categoryService.saveCategory(categoryPageStatekeeper.getCurrentCategory());

            // save category of added product to statekeeper, so you get to see the product immediately in its category
            categoryPageStatekeeper.setCategoryName(categoryDto.getCategoryName());

            categoryPageStatekeeper.clearCurrentCategory();
            categoryPageStatekeeper.setShowCatForm(false);
        }
        return "redirect:/categories";
    }

    @GetMapping("/categories/{categoryCode}")
    protected String editCategory(@PathVariable("categoryCode") String categoryCodeString,
                                  @SessionAttribute("categoryPageStateKeeper") CategoryPageStateKeeper categoryPageStateKeeper) {

        CategoryDto selectedCategory = categoryService.findDtoByCode(Integer.parseInt(categoryCodeString));
        categoryPageStateKeeper.setCurrentCategory(selectedCategory);

        showCatForm(categoryPageStateKeeper);

        return "redirect:/categories";
    }

    @PostMapping("/categories/edit")
    protected String saveExistingCategory(@ModelAttribute("category") CategoryDto categoryDto,
                                          BindingResult result,
                                          @SessionAttribute("categoryPageStateKeeper") CategoryPageStateKeeper categoryPageStateKeeper) {
        if (!result.hasErrors()) {
            categoryPageStateKeeper.setShowCatForm(true);
            categoryPageStateKeeper.setCurrentCategory(categoryDto);
            categoryService.saveCategory(categoryPageStateKeeper.getCurrentCategory());

            clearCatForm(categoryPageStateKeeper);
        }

        return "redirect:/categories";
    }
}
