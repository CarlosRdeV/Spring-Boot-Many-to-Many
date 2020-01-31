CREATE DATABASE  IF NOT EXISTS `Vgames`;
USE `Vgames`;


-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `genero`;

CREATE TABLE `genero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genero` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `juego`;

CREATE TABLE `juego` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `game_year` int(11) DEFAULT NULL,
  `esrb` varchar(5) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,  
  `enable` boolean DEFAULT NULL,  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
DROP TABLE IF EXISTS `plataforma`;

CREATE TABLE `plataforma` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plataforma` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
DROP TABLE IF EXISTS `juego_genero`;

CREATE TABLE `juego_genero` (
  `juego_id` int(11) NOT NULL,
  `genero_id` int(11) NOT NULL,
  PRIMARY KEY (`juego_id`,`genero_id`),
  
  KEY `FK_GENERO_idx`(`genero_id`),
  
  CONSTRAINT `FK_JUEGO_01` foreign key (`juego_id`)
  REFERENCES  `juego`(`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_GENERO_01` foreign key (`genero_id`)
  REFERENCES  `genero`(`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
DROP TABLE IF EXISTS `juego_plataforma`;

CREATE TABLE `juego_plataforma` (
  `juego_id` int(11) NOT NULL,
  `plataforma_id` int(11) NOT NULL,
  PRIMARY KEY (`juego_id`, `plataforma_id` ),
  
  KEY `FK_PLATAFORMA_idx`(`plataforma_id`),
  
  CONSTRAINT `FK_JUEGO_02` foreign key (`juego_id`)
  REFERENCES  `juego`(`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_PLATAFORMA_02` foreign key (`plataforma_id`)
  REFERENCES  `plataforma`(`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
--
--


--
-- Data for table `genero`
--

INSERT INTO `genero` VALUES 
	(1,'Shooter'),
    (2,'Fighting'),
    (3,'RPG'),
    (4,'Sport'),
    (5,'Racing');
    
--
-- Data for table `juego`
--

INSERT INTO `juego` VALUES 
	(1,'DOOM Eternal',2020,'M','Bethesda Softworks',1),
    (2,'Dragon Ball FighterZ',2018,'T','BANDAI NAMCO',1),
    (3,'The Witcher 3: Wild Hunt',2015,'M','CD Projekt Red',1),
    (4,'FIFA 20',2019,'E','Electronic Arts',1),
    (5,'Need for Speed HEAT',2019,'T','Electronic Arts',1);
    
    
INSERT INTO `plataforma` VALUES 
	(1,'Xbox One'),
	(2,'Playstation 4'),
	(3,'Nintendo Switch'),
	(4,'PC');
    
INSERT INTO `juego_genero` VALUES 
	(1,1),
	(2,2),
	(3,3),
	(4,4),
	(5,5);
    
INSERT INTO `juego_plataforma` VALUES
	(1,4),
    (2,3),
    (3,3),
    (4,1),
    (5,2);

    