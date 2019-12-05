recipes = "37"
e1 = 0
e2 = 1

def createNewRecipes():
    global recipes
    newRecipe = int(recipes[e1]) + int(recipes[e2])
    recipes = recipes + str(newRecipe)

def moveElves():
    global e1
    global e2

    e1 = (e1 + (int(recipes[e1]) + 1)) % len(recipes)
    e2 = (e2 + (int(recipes[e2]) + 1)) % len(recipes)

while (len(recipes) < (165061 + 10)):
    createNewRecipes()
    moveElves()
    # print "Recipes are now: " + recipes
print recipes[len(recipes) - 10:]
