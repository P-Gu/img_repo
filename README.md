# img_repo

A simple image repository project started by Peng Gu on 09/06/2020. The main page shows every image in the repository. The user can upload an image and it will show up at the bottom of the main page. 

Backend is implemented with Spring Boot. After the user uploads the title and image file, the image is stored in Amazon S3 and the url to the image is stored in the MySQL database with the title. The main page always fetches urls of all images from MySQL and displays these images. There will be more features including image selection, deletion, etc. in the future.

To build the project, enter root folder in command line and type 
> docker-compose up

After docker compose succeeds, open another command, type
> docker run -p 5000:8080 imagerepo

Now you can go to localhost:5000/findImage in your browser.
