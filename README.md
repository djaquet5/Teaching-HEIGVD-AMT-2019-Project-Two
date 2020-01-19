# Teaching-HEIGVD-AMT-2019-Project-Two
### Useful links

1.  [Setup and use](#setup-and-use) 

2. [Implementation](documentation/implementation.md)

3. [Test strategy](documentation/test_strategy.md)

   1.  [Load Experiment](documentation/load_test_report.md)

4.  [Known Issue](documentation/known_error.md)

    

### Explanation of the project

Nous avons choisi d'implémenter un système de gestion d'arbitrage pour des matchs de football américain. Un match est composé de deux équipes (une équipe `home` ainsi qu'une équipe `away`) ainsi que 7 arbitres ayant des postes et des niveaux différents. Dans notre application, les utilisateurs sont donc les arbitres.

### Setup and Use

Le lancement de l'application se fait en trois étapes:

1. Dans le dossier docker-comp/dev

   ```
   sh ./cleanStartDocker.sh
   ```

2. Aller dans le dossier microservices/auth/spring-server et lancer la commande suivante 

   ```
   mvn spring-boot:run
   ```

3. Aller dans le dossier microservices/officialLeague/spring-server et lancer la commande suivante 

   ```
   mvn spring-boot:run
   ```

Les applications sont désormais lancée aux adresse suivante 

 localhost:8080/api  pour l'application Auth

 localhost:8081/api  pour l'application OfficialLeague



Attention, si vous utiliser docker for windows il faudra changer l'adresse de la base de donnée dans les deux fichier application.properties situé dans le dossier spring-server\src\main\resources de chacune des application

