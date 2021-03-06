#implement these scripts

-- MySQL Script generated by MySQL Workbench
-- Thu Sep 27 20:51:34 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydbLastName
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Customer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Customer` (
  `CustomerID` INT NOT NULL AUTO_INCREMENT,
  `FullName` VARCHAR(20) NOT NULL,
  `EmailAddress` VARCHAR(30) NOT NULL,
  `Address` VARCHAR(50) NOT NULL,
  `PhoneNUM` CHAR(10) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  `City` VARCHAR(20) NULL,
  `Country` VARCHAR(20) NULL,
  `Zipcode` CHAR(6) NULL,
  PRIMARY KEY (`CustomerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Shipper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Shipper` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Shipper` (
  `ShipperID` SMALLINT NOT NULL,
  `EmailAddress` VARCHAR(30) NOT NULL,
  `PassWord` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ShipperID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Collector`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Collector` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Collector` (
  `CollectorID` SMALLINT NOT NULL AUTO_INCREMENT,
  `EmailAddress` VARCHAR(30) NOT NULL,
  `PassWord` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`CollectorID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Shipping`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Shipping` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Shipping` (
  `ShippingID` INT NOT NULL AUTO_INCREMENT,
  `CustomerID` INT NOT NULL,
  `LargeBoxQuantity` TINYINT NOT NULL,
  `MediumBoxQuantity` TINYINT NOT NULL,
  `SmallBoxQuantity` TINYINT NOT NULL,
  `CustomerMessage` VARCHAR(100) NULL,
  `PreferredDeparture` DATE NOT NULL,
  `EstimateArrival` DATE NOT NULL,
  `BookingTime` TIMESTAMP NOT NULL,
  `Cost` DECIMAL(6,2) NOT NULL,
  `ShipperID` SMALLINT NULL,
  `Status` ENUM('1', '2', '3', '4', '5', '6', '7', '8') NOT NULL,
  `AcknowledgeTime` TIMESTAMP NULL,
  `DepartureDate` DATE NULL,
  `ShipperMessage` VARCHAR(100) NULL,
  `PickupTime` DATETIME NULL,
  `HBL` CHAR(20) NULL,
  `CollectorID` SMALLINT NULL,
  `PickupCity` VARCHAR(20) NOT NULL,
  `PickupAddress` VARCHAR(20) NOT NULL,
  `PickupZipcode` CHAR(6) NOT NULL,
  `DeliveryCity` VARCHAR(20) NOT NULL,
  `DeliveryAddress` VARCHAR(50) NOT NULL,
  `DeliveryZipcode` CHAR(6) NOT NULL,
  PRIMARY KEY (`ShippingID`),
  INDEX `fk_Shipping_Customer1_idx` (`CustomerID` ASC),
  INDEX `fk_Shipping_Shipper1_idx` (`ShipperID` ASC),
  INDEX `fk_Shipping_Collector1_idx` (`CollectorID` ASC),
  CONSTRAINT `fk_Shipping_Customer1`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `mydb`.`Customer` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Shipping_Shipper1`
    FOREIGN KEY (`ShipperID`)
    REFERENCES `mydb`.`Shipper` (`ShipperID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Shipping_Collector1`
    FOREIGN KEY (`CollectorID`)
    REFERENCES `mydb`.`Collector` (`CollectorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Customer_has_Shipping`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Customer_has_Shipping` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Customer_has_Shipping` (
  `Customer_CustomerID` INT NOT NULL,
  `Shipping_ShippingID` INT NOT NULL,
  PRIMARY KEY (`Customer_CustomerID`, `Shipping_ShippingID`),
  INDEX `fk_Customer_has_Shipping_Shipping1_idx` (`Shipping_ShippingID` ASC),
  INDEX `fk_Customer_has_Shipping_Customer_idx` (`Customer_CustomerID` ASC),
  CONSTRAINT `fk_Customer_has_Shipping_Customer`
    FOREIGN KEY (`Customer_CustomerID`)
    REFERENCES `mydb`.`Customer` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_has_Shipping_Shipping1`
    FOREIGN KEY (`Shipping_ShippingID`)
    REFERENCES `mydb`.`Shipping` (`ShippingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PreferredPickup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PreferredPickup` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PreferredPickup` (
  `ShippingID` INT NOT NULL,
  `StartTime` DATETIME NOT NULL,
  `EndTime` DATETIME NOT NULL,
  PRIMARY KEY (`ShippingID`, `StartTime`, `EndTime`),
  CONSTRAINT `fk_PreferredPickup_Shipping1`
    FOREIGN KEY (`ShippingID`)
    REFERENCES `mydb`.`Shipping` (`ShippingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
