# Snake Game

## Introduction

Bienvenue dans le jeu du serpent (Snake Game) ! Ce projet implémente le jeu classique du serpent en utilisant Java et JavaFX. Le jeu propose une interface graphique pour une expérience utilisateur améliorée.

## Fonctionnalités

- Plateau de jeu avec des cellules vides, de la nourriture et le serpent.
- Contrôle du serpent par les touches directionnelles.
- Possibilité d'avoir deux joueurs au clavier.
- Détection des collisions avec les autres joueurs et le serpent lui-même et les rebords.
- Personnalisation des règles "aérodynamiques" : augmentation de la vitesse du serpent.
- Règles "boucliers" : évitent une mort immédiate lors d'une collision si le segment en tête possède un bouclier.
- Terrain sans rebords en option.
- Mouvement fluide du serpent, sans sauts brusques.
- Possibilité de jouer contre une intelligence artificielle.

## Compilation et Exécution

Le projet peut être compilé et executé en utilisant Visual Studio Code en appuyant sur Run .

## Tests

Le projet est livré avec des tests unitaires pour vérifier le bon fonctionnement des composants clés. Vous pouvez exécuter les tests en utilisant Gradle avec la commande suivante :

./gradlew test

## Utilisation

Utilisez les touches directionnelles (d, q, s, z) pour contrôler le serpent.
Pour deux joueurs au clavier, le deuxieme joueur utilisera les touches (haut, bas, gauche, droite)
Évitez les collisions avec les bords du plateau et avec le serpent lui-même.
Collectez la nourriture pour augmenter la taille du serpent.
Expérimentez avec les règles "aérodynamiques" et les règles "boucliers".
Choisissez un terrain avec ou sans rebords selon vos préférences.


## Choix Techniques

Utilisation de JavaFX pour l'interface graphique.
Modélisation du jeu avec des classes telles que Snake, BoardGame, et Position.
Utilisation de Gradle pour la gestion des dépendances et l'exécution des tests.
Implémentation de la détection de collisions pour assurer un gameplay cohérent.
Intégration de règles personnalisées pour une expérience de jeu variée.

## Note :
La classe `Position` utilise un type générique `U` pour permettre une flexibilité maximale dans le type de données que la position peut contenir. La classe `Game` est implémentée en tant que singleton, assurant qu'il n'existe qu'une seule instance du jeu en cours à tout moment.