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
afin de constater leur efficacité.

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

Implémentation 2: 
-----------------
Cette implémentation selon Boyer-Moore se fait en deux parties distinctes :
Tout d'abord, On fait appel à la fonction remplirBonSuffixe qui se charge de remplir la table des bons suffixes du motif passé en paramètre. C'est elle qui va déterminer le nombre de lettres à shifter lors de la recherche de motif.

Pour un motif m de taille n, on procède à chaque indice de 0 à n-1 de telle façon : La case d'indice x compris entre 0 et n-1 est égale à une valeur v. Concrètement, cela veut dire que nous avons trouvé le mot m[x-1,n-1] dans une séquence mais que m[x] n’apparaît pas dans la dite séquence à sa bonne place. Dans ce cas il faut se décaler de v indices sur la droite dans la séquence pour espérer trouver une occurrence du motif. 
	
L'algorithme fonctionne ainsi pour un motif m de longueur n passé en paramètre :
Pour chaque sous-motif du mot (c'est à dire pour chaque m[i,n-1],  pour i = n-1…0) :
On doit chercher une occurrence du sous-motif mais qui ne doit pas être égal à m[i-1,n-1] à l'intérieur même du motif.
Si on en trouve une alors on écrit dans bonsuffixe[i-1] le nombre de déplacement pour se rendre à cette occurrence à partir de i.
Sinon bonsuffixe[i-1] vaut n moins la longueur de plus long bord du motif.

Puis l'algorithme principal chercherMotif fait appel à remplirBonSuffixe dans un premier temps. Puis on commence à chercher les occurrences du motif m dans la séquence s. 
i vaut 0 au début.
On compare s[x+i] et m[x], x décroissant de n-1 à 0 (n étant la taille du motif).
Dès que l'on a une différence de caractères pour un des x, alors i = i + bonsuffixe[x].
Si l'on trouve une occurrence du motif dans la séquence alors on ajoute son indice dans la liste des occurrences et i = i + bonsuffixe[i].
On recommence les comparaisons jusqu'à la fin de la séquence entrée en paramètre.
	
A la fin on retourne cette liste.

Etat du travail :
=================

Travail réalisé :
-----------------
+ Lecture d'un brin dans un fichier fasta
+ Recherche d'une chaîne de nucléotides donnée dans un brin (retour des occurences
sous forme d'une liste d'indices) : Algo Naïf et Boyer-Moore.
+ Calcul des différentes versions d'une chaîne (reverse, complementaire, ...)
+ Modifier le Main pour être capable de lire les options (taille, booléens et 
fichier) de la recherche
+ Écrire fonction permettant de calcul chaque séquence de taille N et de rechercher 
leurs occurences (voir notes)
+ Fusionner dans la recherche les résultats en fonction des booléens (si réverse est
à true, les résultats d'un mot et de son réverse devront être fusionnés) 
+ Ecrire les résultats dans un fichier dotplot
+ Factoriser le code du Main (trop grand)
+ Tester l'ensemble du programme
+ Réduire la complexité des fonctions de la classe Recherche notamment
la fusion des résultats selon les options.
+ Rendu :
	- sources commentées
	- documentation
	- exécutable
	- rapport (4 pages Max) : 
		- présentation des algorithmes
		- explication des résultats (dotplot + détection mirna)

Notes :
-------
La fonction de recherche globale commencera par un parcours du brin en ajoutant chaque 
motif différents composés de N nucléotides dans une Map (avec une liste vide).
Il suffira ensuite de parcourir la liste des clés et de rechercher chaque motif en 
ajoutant le résultat de la recherche comme valeur de la Map.
A la fin de la recherche complète, il faudra également, en fonction des options de
recherche, fusionner les valeurs des clés complémentaires/inverses/...

