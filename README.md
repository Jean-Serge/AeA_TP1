TP1 - AeA
=========
Jean-Serge Monbailly
Thibaud Verbaere

Ce TP porte sur la recherche de motif dans un brin d'ADN.
Ce TP est réalisé en JAVA.

Utilisation :
=============
Compléter avec le fonctionnement du programme :
+ commandes pour lancer le programmes
+ commandes pour exécuter les tests

Fonctionnalités :
=================

Le programme réalisé permet : 
+ de lire un fichier .fasta afin d'en extraire le brin à étudier
+ de rechercher dans un brin une chaîne de nucléotide donnée et 
de retourner l'indice de chaque occurence de cette chaîne
+ pour chaque chaîne, être également capable de recherche son
inverse, son complémentaire et son inverse-complémentaire
+ de rechercher les occurences de toutes les chaînes de longueur
N (N étant indiqué en paramètre)
+ d'écrire les résultats de ces recherches dans un fichier "dotplot"

Implémentation :
================

Nous allons implémenter différents algorithme de recherche de motif 
afin de constater leur efficacité à chacun.


Implémentation 1:
-----------------
Cette première implémentation est réalisée en utilisant l'algorithme naïf.
Cet algorithme parcours un par un de chaque nucléotide du brin pour les comparer 
au motif recherché. Il est en O(n x m) (où n est la longueur du brin et m est
la taille du motif).

L'implémentation de cet algorithme simple nous permet de disposer d'un programme
fonctionnel assez rapidement. Cela nous permet également de vérifier efficacement
le résultat d'algorithmes plus complexes par la suite.


Etat du travail :
=================

Travail réalisé :
-----------------
+ Lecture d'un brin dans un fichier fasta
+ Recherche d'une chaîne de nucléotides donnée dans un brin (retour des occurences
sous forme d'une liste d'indices)
+ Calcul des différentes versions d'une chaîne (reverse, complementaire, ...)

TODO :
------
- Écrire fonction permettant de calcul chaque séquence de taille N et de rechercher 
leurs occurences
- Modifier le Main pour être capable de lire les options (taille, booléens et 
fichier) de la recherche
- Fusionner dans la recherche les résultats en fonction des booléens (si réverse est
à true, les résultats d'un mot et de son réverse devront être fusionnés) 
- Tester l'ensemble du programme
- Commencer l'implémentation d'un autre algorithme de recherche
