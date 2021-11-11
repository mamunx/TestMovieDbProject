# Application Built for SetSchedule as a Test Project

This is an android application build as a test project. The app fetches some movies that are 
currently being played. It also has a search mechanism, where you can search by a query text, movie
year and should show adult content. Items from both type of list (Now showing and Searched List) can
be clicked and a new page showing the details of that specific movie will be shown. 

## API Info

I've used the free API named The Movie DB to get the data. I've used two of their APIs. One is now 
playing movies, another is Search movies.

### Features

    1. Upon opening the app, an API is called and now playing movies are fetched
    2. Clicking on any item will take to the details page.
    3. Backing from both the keyboard press and toolbar back press will take user back.
    4. Now Playing Page toolbar has a search button.
    5. Clicking the search button will open a dialog. The dialog takes 3 inputs, only the query is
        required, adult content is false by default.
    6. Validation is added in the filter inputs.
    7. Clicking on the search button, An API is called and data is shown.
    8. Clicking on any item will again take the user to a details page.
    9. Pressing back from the Search page will take user back to the Now Playing page, it works
        seamlessly because State is managed in the page.