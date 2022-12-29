CREATE SCHEMA IF NOT EXISTS `condominio` DEFAULT CHARACTER SET utf8mb4 ;
USE `condominio` ;
DROP TABLE `condominio`.`usuarios_papeis`;
DROP TABLE `condominio`.`papeis`;
DROP TABLE `condominio`.`usuarios`;

CREATE TABLE IF NOT EXISTS `condominio`.`proprietario` (
  `id_proprietario` INT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `telefone` VARCHAR(45) NULL,
  PRIMARY KEY (`id_proprietario`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `condominio`.`apartamento` (
  `id_apartamento` INT NULL AUTO_INCREMENT,
  `qtde_quartos` INT NULL,
  `nro_porta` INT NULL,
  `tipo` VARCHAR(45) NULL,
  `proprietario_id_proprietario` INT NOT NULL,
  PRIMARY KEY (`id_apartamento`, `proprietario_id_proprietario`),
  FOREIGN KEY (`proprietario_id_proprietario`)
    REFERENCES `condominio`.`proprietario` (`id_proprietario`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `condominio`.`papeis` (
  `papel_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`papel_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `condominio`.`usuarios` (
  `usuario_id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`usuario_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `condominio`.`usuarios_papeis` (
  `usuario_id` INT(11) NOT NULL,
  `papel_id` INT(11) NOT NULL,
  CONSTRAINT `papel_fk`
    FOREIGN KEY (`papel_id`)
    REFERENCES `papeis` (`papel_id`),
  CONSTRAINT `user_fk`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `condominio`.`usuarios` (`usuario_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;




