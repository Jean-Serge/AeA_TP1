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

Le programme attend comme paramètres : 
+ Le chemin vers un fichier .fasta
+ Un entier N
+ 3 paramètres (facultatifs) indiquant les options de 
recherche


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

Les résultats d'une recherche de motif seront stockés dans une liste 
d'entiers représentant les occurences de la chaîne.
Les résultats d'une recherche complète seront stockés dans une Map 
dont la clé sera un motif et dont les valeurs seront des listes
d'entiers.


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
+ Modifier le Main pour être capable de lire les options (taille, booléens et 
fichier) de la recherche
+ Écrire fonction permettant de calcul chaque séquence de taille N et de rechercher 
leurs occurences (voir notes)
+ Fusionner dans la recherche les résultats en fonction des booléens (si réverse est
à true, les résultats d'un mot et de son réverse devront être fusionnés) 

TODO :
------
+ Tester l'ensemble du programme
+ Commencer l'implémentation d'un autre algorithme de recherche
+ Implémenter l'écriture des résultats dans un fichier dotplot
+ Factoriser le code du Main (trop grand)

Notes :
-------
La fonction de recherche globale commencera par un parcours du brin en ajoutant chaque 
motif différents composés de N nucléotides dans une Map (avec une liste vide).
Il suffira ensuite de parcourir la liste des clés et de rechercher chaque motif en 
ajoutant le résultat de la recherche comme valeur de la Map.
A la fin de la recherche complète, il faudra également, en fonction des options de
recherche, fusionner les valeurs des clés complémentaires/inverses/...

