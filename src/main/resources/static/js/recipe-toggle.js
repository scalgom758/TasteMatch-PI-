  const recipeList = document.querySelector('.recipe-list');
  const noRecipes = document.querySelector('.no-recipes');

  function updateNoRecipesVisibility() {
    const recipeBlocks = recipeList.querySelectorAll('.recipe-block');
    
    if (recipeBlocks.length === 0) {
      noRecipes.style.display = 'grid';
    } else {
      noRecipes.style.display = 'none';
    }
  }

  updateNoRecipesVisibility();

  const observer = new MutationObserver(updateNoRecipesVisibility);
  observer.observe(recipeList, { childList: true });