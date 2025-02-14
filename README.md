# Logan's Pizzeria

Welcome to my pizzeria! This application allows for a user to operate as either the owner of the restaurant, or a chef. The owner is able to create, edit, and delete
pizza toppings, while chefs can create, edit, and delete pizzas.

## Features

All:
- View toppings and pizzas
- Search for specific toppings and pizzas
- Change role as needed

Owner:
- Create toppings
- Edit toppings (edited toppings will be reflected in pizzas)
- Delete toppings (deleted toppings will be reflected in pizzas)

Chef:
- Create pizzas
- Edit pizzas
- Delete pizzas

## Getting Started

**Before beginning, ensure that you have visual studio code, or equivalent IDE, installed**

To get started with the application, follow these steps:

1. **Clone the Repository**: 
   ```bash
   git clone https://github.com/logan-campbell27/pizzeria

2. **Create Your Local Database**

   Copy the following SQL script in your SQL editor of choice to create the schema and tables associated with the app:
   ```bash
   -- phpMyAdmin SQL Dump
   -- version 5.2.0
   -- https://www.phpmyadmin.net/
   --
   -- Host: localhost:8889
   -- Generation Time: Feb 14, 2025 at 09:09 PM
   -- Server version: 5.7.39
   -- PHP Version: 8.2.0
   
   SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
   START TRANSACTION;
   SET time_zone = "+00:00";
   
   
   /*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
   /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
   /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
   /*!40101 SET NAMES utf8mb4 */;
   
   --
   -- Database: `pizzeria`
   --
   CREATE DATABASE IF NOT EXISTS `pizzeria` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
   USE `pizzeria`;
   
   -- --------------------------------------------------------
   
   --
   -- Table structure for table `pizzas`
   --
   
   CREATE TABLE `pizzas` (
     `id` int(11) NOT NULL,
     `creator` varchar(50) NOT NULL,
     `name` varchar(50) NOT NULL,
     `toppings` varchar(250) NOT NULL
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
   -- --------------------------------------------------------
   
   --
   -- Table structure for table `toppings`
   --
   
   CREATE TABLE `toppings` (
     `id` int(11) NOT NULL,
     `creator` varchar(50) NOT NULL,
     `name` varchar(50) NOT NULL,
     `description` varchar(250) NOT NULL
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
   --
   -- Indexes for dumped tables
   --
   
   --
   -- Indexes for table `pizzas`
   --
   ALTER TABLE `pizzas`
     ADD PRIMARY KEY (`id`);
   
   --
   -- Indexes for table `toppings`
   --
   ALTER TABLE `toppings`
     ADD PRIMARY KEY (`id`);
   
   --
   -- AUTO_INCREMENT for dumped tables
   --
   
   --
   -- AUTO_INCREMENT for table `pizzas`
   --
   ALTER TABLE `pizzas`
     MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
   
   --
   -- AUTO_INCREMENT for table `toppings`
   --
   ALTER TABLE `toppings`
     MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
   COMMIT;
   
   /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
   /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
   /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

3. **Update Connection Details**

   - Locate and open the file application.properties in the src/main/resources folder
   - Change the connection details to be compatible with your SQL database
   ```bash
   spring.datasource.url=jdbc:mysql://*HOST URL*/pizzeria
   spring.datasource.username=*YOUR USERNAME HERE*
   spring.datasource.password=*YOUR PASSWORD HERE*
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

4. **Run the Application**

   - The app should now be able to run!
   - Navigate to src/main/java/StrongMind/Pizzeria.java and click the run button located in your IDE
   - The application should now start, and the landing page can be found on localhost:8080

## Technologies Used:
- Java SpringBoot
- ThymeLeaf
- MySQL
- Heroku

I chose Java SpringBoot because it contains many built in features that make interacting with a database seamless and simple, along with feeling comfortable coding in a Java 
environment. I chose ThymeLeaf because it is very easy to integrate with SpringBoot, and felt it was a good choice for the design features this project required. I chose to
use a MySQL database as it is what I have used in previous projects, and I already possessed the proper software to utilize it. Finally, I chose Heroku as my hosting platform
as it is very simple and lightweight, and allows for easy use of CI/CD due to its integration with GitHub
