#!/bin/bash

### Cette commande permet d'effectuer ue recherche de motif dans un fichier
### .fasta, il est paramétrable avec la taille des motifs à rechercher ainsi
### que les éventuelles options de la recherche.

###
### Indique à l'utilisateur comment utiliser cette commande.
###
usage()
{
    echo
    echo "Pour utiliser ce programme vous devez spécifier les paramètres suivants :"
    echo "  - le chemin vers le fichier .fasta à lire"
    echo "  - un entier correspondant à la taille des mots à rechercher"
    echo
    echo "Vous pouvez également spécifier les options de recherche (facultatives) suivantes :"
    echo "  - -r pour rechercher les mot de taille N et leur inverse"
    echo "  - -c pour rechercher les mot de taille N et leur complémentaire"
    echo "  - -rc pour rechercher les mot de taille N et leur inverse-complémentaire"
    echo
    echo "Ce programme compilera les sources et exécutera l'application Java avec les paramètres spécifiés."
    echo "Cette application créera ensuite un fichier .plot et .txt qui serviront à générer le plot final."
}


# Compilation
if [ ! -d bin ];
then
    mkdir bin
fi
javac -d bin src/*/*.java


# Lecture des options indiquées
option=''
for i in "$@" 
do
    option=$option" "$i
done

# Exécution
java -cp bin main.Main $option

if expr $? = 0 >/dev/null 
then
    
    if [ -f 'resultats.plot' ];
    then
	gnuplot resultats.plot 2>/dev/null
    fi

    # Vérifie que gnuplot est installé
    if expr $? != 0 >/dev/null 
    then 
	echo "gnuplot doit être installé pour pouvoir générer le resultat."
    else
	echo "Pour voir les résultats, visionnez le fichier resultats."
    fi
else
    # Si problème avec le programme Java
    usage
fi
