#!/bin/bash

### Cette commande permet d'effectuer ue recherche de motif dans un fichier
### .fasta, il est paramétrable avec la taille des motifs à rechercher ainsi
### que les éventuelles options de la recherche.

option=''
for (( i=0 ; i<$# ; i++ )) 
do
	$option=($option + $$i)
done

echo $option
