#  Bienvenue à Chefs House ! 

Bonjour, professionnels de la construction et de la rénovation ! 

Bienvenue sur **Chefs House**, votre application Java avancée, spécialement conçue pour répondre aux besoins des experts en rénovation et construction de cuisines. **Chefs House** simplifie la gestion des clients, la génération de devis pour vos projets et le suivi financier et logistique de l'ensemble de vos travaux.

##  À propos de Chefs House

**Chefs House** est conçu pour offrir aux professionnels de la rénovation et de la construction un outil performant permettant d’estimer les coûts des matériaux et de la main-d'œuvre impliqués dans les projets de cuisine. L'application inclut des fonctionnalités avancées telles que la gestion des clients, la génération de devis personnalisés et une vue complète des finances et de la logistique du projet. Avec **Chefs House**, vous pouvez calculer les coûts horaires de main-d'œuvre et suivre l'utilisation des matériaux, simplifiant ainsi la gestion des projets.

## Structure du Projet

Voici une vue d'ensemble de la structure du projet pour **Chefs House** :

- **config** : Gère les paramètres de configuration de l'application : `DatabaseConnection`
- **models** : Définit les modèles représentant les clients, les projets, les devis, les matériaux, les composants et la main-d'œuvre.
- **repositories** : Contient les classes de repository pour les opérations sur la base de données.
- **services** : Couche de logique métier pour gérer les fonctionnalités principales telles que les projets, les clients et les matériaux.
- **ui** : Contient la classes `MainMenu`,  qui gère les interactions avec l'utilisateur via une interface en ligne de commande.
- **utils** : Classes utilitaires pour gérer des fonctions supplémentaires et des validations.
- **resources** : Stocke les propriétés de l'application et les scripts SQL.
    - `db.sql` : Fichier SQL pour créer le schéma de base de données nécessaire.

## Fonctionnalités Principales

-  **Gestion des Projets** : Créez, visualisez et gérez les projets de rénovation pour vos clients.
-  **Devis Personnalisés** : Générez des devis détaillés basés sur les matériaux et les coûts horaires de main-d'œuvre.
-  **Gestion des Clients** : Gérez les informations de vos clients, y compris la récupération de leurs projets associés.
-  **Types de Composants** : Ajoutez, modifiez, supprimez ou visualisez les types de composants utilisés dans les projets de rénovation.
-  **Suivi Financier** : Suivez les coûts associés à chaque projet, y compris les matériaux et la main-d'œuvre.

## Comment Utiliser Chefs House

### Prérequis

Assurez-vous d'avoir installé les éléments suivants sur votre machine :

- **Java 8** ou version ultérieure
- **Base de données PostgreSQL** avec les tables et le schéma nécessaires (instructions ci-dessous)
- **Pilote JDBC** pour PostgreSQL
- Un terminal ou une console pour exécuter l'application

### Installation

1. Clonez ce dépôt sur votre machine locale :
   ```bash
   git clone https://github.com/Rayane20777/Chefs_House
   cd Chef_House
   cd out/artifacts/Chef_House
   java -jar Chef_House.jar
