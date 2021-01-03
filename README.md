<h1>About this project</h1>
<p>This application use API from https://punkapi.com/documentation/v2 and makes a list of all available beers in the main page. There are several features in this application that are mentioned below:</p>
<ul>
<li>Get list of beers:  Fetch all beers with details from the mentioned URL by Retrofit.</li>
<li>Sorting by ABV, IBU and EBC: The user is able to sort beers by all ABV, IBU and EBC keywords. In the general situation the beers are sorted by their ID.</li>
<li>Like: The user is able to like each beer in its single view. There is a like icon on the top right side of each view. If the user likes an item, then it will store in the “Liked Items”. “Liked Items” are accessible from the main page, by tapping on the “Like” icon on the top right. </li>
<li>No internet access: If the user will not have access to the internet. The application will still works and appear the list of beers from its internal database(Save data in a local database using Room)</li>
<li>Detail of each beer: By clicking on each item in the main page, the user will rout to the single view of Beer’s page. There are 25 items fetched from the API and loaded in the single view of each beer’s page. </li>
<li>Pagination: If the user will scroll the list in the main page, the application will load its list by a pagination and the user is allowed to scroll and get all the items that are coming from the API.</li>
<li>Dark mode: By enabling the Dark mode setting in the phone, the application will show its dark mode theme. And by bringing back to the normal theme, then the app will show its day mode theme.</li>
</ul>


<h3>How to see the source code:</h3> 
<ul>
<li>Pull the code from the Github repository - Master branch (Link).</li>
<li>Open Android Studio and select 'Open an existing Android Studio Project'</li>
<li>Navigate to the repository.</li>
<li>Run the application.</li>
</ul>
 
<h3>Android Version Targeting:</h3> 
<ul>
<li>compileSdkVersion 30</li>
<li>buildToolsVersion 30.0.2</li>
<li>minSdkVersion 21</li>
<li>targetSdkVersion 30</li>
</ul>
 
<h3>Technologies:</h3> 
<ul>
<li>Kotlin</li>
<li>MVP</li>
<li>Retrofit</li>
<li>Room persistence</li>
<li>Lambda Expressions</li>
<li>Executors</li>
<li>Handlers</li>
<li>JUnit</li>
<li>Pagination</li>
<li>Both Light and Dark mode Supported </li>
<li>"Roboto" font supported </li>
<li>Constraintlayout</li>
<li>Material Design</li>
</ul>
 
<h3>Test Room DB</h3>
<p>You can test and debug the database. The recommended approach for testing database implementation is writing a JUnit test that runs on an Android device. Because these tests don't require creating an activity, they should be faster to execute than your UI tests.

<h3>res/*</h3> 
The fob_solutions.png document here is utilized as the picture to show in one of the perspectives in activity_splash.xml.
<ul>
<li>res/values/colors.xml</li>
<li>res/values/strings.xml</li>
<li>res/values/styles.xml</li>
<li>res/values/dimens.xml</li>
<li>res/values/theme.xml</li>
</ul>
These XML records depict extra assets remembered for the application.
