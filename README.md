# img_repo

A simple image repository project started on 09/06/2020. The main page shows every image in the repository. The user can upload images one at a time and it will show up at the bottom of the main page. 
Backend is implemented with Spring Boot. After the user uploads the title and image file, the image is stored in Amazon S3 and the url to the image is stored in the MySQL database with the title. The main page always fetches urls of all images from MySQL and displays them. There will be more features including image selection, deletion, etc. in the future.
