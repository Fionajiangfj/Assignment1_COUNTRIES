# Assignment1_COUNTRIES_API
 Advanced Android Dev - assignment 1

# Practice on API call and CRUD operations in Google firestore database


**Description:**

An Android application that displays information about countries of the world.
    1. List of World Countries Screen: This screen displays a list of countries
    2. Favorite Country List Screen: This screen displays a list of countries that the user added as favorite
    
# Screen 1: List of World Countries
    1. This screen displays a list of countries in a RecyclerView.
    2. Country data retrieved from this API: https://restcountries.com/v3.1/independent?status=true
    3. Each row of the RecyclerView displays only country name and Favorite button.
        ● In the API, the country name is found under the name > common property.
        ● Clicking on the Favorite button will add the country name to the user’s favorites list. The favorites list is modelled as a Firestore collection.
    4. This screen has an options menu to allow the user to open the favorite list screen. (screen 2)

# Screen 2: Favorites Screen
    1. This screen retrieves the user’s list of favorite countries from Firestore and display them in RecyclerView.
    2. Each row shows the country name.
    3. Users can tap on the RecyclerView item to remove the country from the user’s favorites list.
