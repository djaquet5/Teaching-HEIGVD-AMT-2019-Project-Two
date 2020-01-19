# Implementation

Pour ce projet, nous avons repris les données de notre précédent projet. Nous avons donc un projet permettant la gestion des arbitres (*officials*) dans une ligue de football américain. Comme demandé, notre projet a été séparé en 2 `API` :

1. La première permet aux utilisateurs de s'authentifier
2. La seconde contient le reste des éléments de la ligue (équipes et matchs)

## Authentication service

Le service d'authentification contient la liste utilisateurs qui sont des arbitres. Cette partie se connecte à une base de données `User` contenant les éléments suivants :

| Champ       | Type           | Remarque                   |
| ----------- | -------------- | -------------------------- |
| `id`        | `INT`          | Incrémentation automatique |
| `firstname` | `VARCHAR(45)`  |                            |
| `lastname`  | `VARCHAR(45)`  |                            |
| `email`     | `VARCHAR(45)`  | Valeur unique              |
| `password`  | `VARCHAR(255)` |                            |
| `role`      | `VARCHAR(45)`  |                            |

### Paths

#### /connection

Cette route contient uniquement une méthode `POST`. Cette dernière permet à un utilisateur de s'authentifier et prend en paramètre un **email** ainsi qu'un **mot de passe**. Il est important que le mot de passe doit être en clair et sera hashé par le back-end.

Cette route renvoie un `JWT Token`.

#### /users

Cette route a besoin d'un `JWT Token` quel que soit la méthode que nous accédons. Il est important de noter que pour effectuer un `POST`, nous avons besoin d'avoir les droits administrateurs. Il faut donc être connecté avec un utilisateur ayant le champ `role` définit à `admin`.

#### /users/{userid}

Comme pour la route précédente, celle-ci a besoin d'un `JWT Token`. Toujours comme pour la route précédente, nous avons besoin d'un droit administrateur pour effectuer les requêtes `PUT` et `DELETE`.

Nous avons choisi d'implémenter un `PUT` (et non pas un `PATCH`) pour les mises à jour d'information pour des raisons de simplicité d'implémentation.

## Official League Service

Ce service contient trois entités distinctes : 

1. Les équipes
2. Les arbitres
3. Les matchs

Les arbitres ont été également implémenté ici afin de pouvoir faire le lien avec les deux autres entités.

### Paths

Pour toutes les requêtes, nous voulions que l'utilisateur soit connecté. C'est pour quoi toutes les routes demandent l'utilisation d'un `JWT Token`.

Pour toutes les entités, toutes les requêtes du `CRUD` ont été implémentées. Cependant, pour les requêtes de création (`POST`), de modification (`PUT`) et de suppression (`DELETE`), l'utilisateur doit disposer des droits administrateur.

Il est important de noter qu'un utilisateur peut modifier ses propres informations sur l'entité `official` sans être administrateur. De plus, la suppression en cascade est supprimée. Ce qui veut dire que nous ne pouvons pas supprimer une équipe ayant arbitré.

La date est stocké au fromat `String` pour des raisons de simplicités.