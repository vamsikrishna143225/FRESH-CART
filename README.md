Fresh Cart is an Android-based mobile application designed to provide users with a seamless and intuitive online shopping experience for fresh fruits, vegetables, and other groceries. The app allows users to browse a variety of items, add them to their cart, and proceed with a smooth checkout process. With an easy-to-use interface, real-time location services, and personalized features, Fresh Cart aims to simplify grocery shopping and enhance the user experience.

Key Features:
1.	User Authentication:
o	Secure user login with validation against stored credentials.
o	New users can sign up easily and access the app.
o	Provides personalized greetings and session management.
2.	Item Browsing:
o	Displays a grid of available fresh items (fruits, vegetables) with images and names.
o	Users can view the details of each item and add them to their shopping cart.
3.	Shopping Cart:
o	Users can add items to the cart and see the total number of items in the cart.
o	The cart is linked to a database, allowing users to view, remove, and manage items in their cart.
4.	Order Placement:
o	Fetches the user's current location using GPS to provide delivery options.
o	Users can confirm their order and proceed to payment or further actions.
5.	Database Integration:
o	User credentials and cart items are stored in an SQLite database for efficient data management.
o	Users’ cart items can be added, removed, or viewed from the database.
6.	Location-Based Services:
o	Real-time location fetching using FusedLocationProviderClient to display the user's address for order delivery.
o	Ensures accurate location data for an efficient shopping and delivery process.
7.	Splash Screen and Navigation:
o	A welcoming splash screen introduces the app before transitioning to the login page.
o	Navigation is simple, with a menu that allows users to quickly switch between different features like cart, order tracking, and profile management.
8.	User-Friendly Interface:
o	Intuitive design with clear icons, buttons, and text views for ease of use.
o	A navigation drawer for easy access to different sections of the app.
9.	Seamless Checkout Process:
o	After adding items to the cart, users can proceed to the "Place Order" screen where they confirm the items and view their location.
o	The "Final Activity" confirms the order and provides the next steps for order processing.
