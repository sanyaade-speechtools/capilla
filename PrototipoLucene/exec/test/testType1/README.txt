Este directorio contiene las pruebas realizadas con los siguientes parámetros:

configurationIndex.txt

#Fichero de configuración para la indexación
analyzer=SnowballAnalyzer
language=Spanish
path_stopper=exec/stopper/spanishSmart.txt
path_files_to_index=exec/docs
path_output_index=exec/index
categorie.name=categorie
categorie.store=YES
categorie.index=NOT_ANALYZED
categorie.term_vector=NO
question.name=question
question.store=YES
question.index=ANALYZED
question.term_vector=NO
answerIndex.name=answerIndex
answerindex.store=YES
answerindex.index=NOT_ANALYZED
answerindex.term_vector=NO
answer.name=answer
answer.store=YES
answer.index=NOT_ANALYZED
answer.term_vector=NO
link.name=link
link.store=YES
link.index=NOT_ANALYZED
link.term_vector=NO
url.name=url
url.store=YES
url.index=NOT_ANALYZED
url.term_vector=NO

********************************************************************************
********************************************************************************
configurationSearch.txt

#Fichero de configuración para la búsqueda
language=Spanish
search_analyzer=SnowballAnalyzer
path_stopper=exec/stopper/spanishSmart.txt
search_field=question
search_field_appear=SHOULD
search_index=exec/index
search_n_results=10
search_explanation=YES
question.name=question
answer.name=answer
url.name=url
