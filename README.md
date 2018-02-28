# Versione-3
Questa versione gestisce il prestito delle risorse (finora solo libri), che è disciplinato dai
seguenti vincoli, fissati individualmente per ciascuna categoria:
- durata massima del prestito, in numero di giorni; ad esempio, per la categoria “libri”
tale durata può essere di 30 giorni;
- durata massima della proroga (non rinnovabile) di un prestito, in numero di giorni;
ad esempio, per la categoria “libri” tale durata può essere di 30 giorni;
- intervallo di tempo entro cui è richiedibile la proroga di un prestito; la richiesta della
proroga di un prestito deve avvenire entro la scadenza del prestito stesso ma non
prima di un numero prefissato di giorno precedenti tale scadenza, ad esempio 3
giorni per la categoria “libri”;
- numero massimo di risorse che possono essere contemporaneamente godute in
prestito dal medesimo fruitore; ad esempio, un fruitore può godere del prestito di 3
libri al più. Si noti che, se una categoria è suddivisa in sottocategorie, il fruitore può
godere contemporaneamente del prestito di risorse appartenenti a sottocategorie
diverse della categoria stessa ma il numero totale delle risorse tenute
contemporaneamente in prestito non deve superare il numero massimo stabilito per
la categoria. Ad esempio, un fruitore può godere contemporaneamente del prestito
di opere appartenenti a sottocategorie diverse della categoria “libri”, purché il
numero totale delle opere in prestito non sia superiore a 3.
La versione corrente permette al fruitore di ottenere risorse in prestito (senza alcun
intervento da parte dell’operatore), compiendo le seguenti operazioni:
 registrazione (in archivio) del prestito di una risorsa disponibile a un fruitore (che
l’ha richiesta) per un numero di giorni pari alla durata del prestito prevista per la
categoria di appartenenza della risorsa, previo controllo del fatto che il numero di
risorse attualmente in prestito al fruitore non sia già quello massimo previsto per
la categoria di appartenenza della risorsa;
 registrazione (in archivio) della proroga del prestito di una risorsa a un fruitore per
un numero di giorni pari alla durata massima della proroga prevista per la
categoria di appartenenza della risorsa, previo controllo del fatto che tale risorsa
sia effettivamente attualmente in prestito al fruitore e che la richiesta di proroga
sia stata avanzata da tale fruitore nell’intervallo di tempo previsto per la categoria
di appartenenza della risorsa;
 terminazione (automatica) del prestito di una risorsa in corrispondenza della data
di scadenza del prestito o della sua proroga (se concessa).
Infine, la versione corrente deve consentire, sia all’operatore sia al fruitore, di effettuare
interrogazioni dell’archivio, secondo le specifiche seguenti:
 ricerca (della descrizione) di una risorsa in archivio in base a valori diversi, ad
esempio, per la categoria “libri”, cognome di uno degli autori, parola contenuta
nel titolo, ecc.;
 valutazione della disponibilità attuale di una risorsa (la risorsa è disponibile per il
prestito se il numero di prestiti/proroghe attivi per la stessa è inferiore al numero
delle sue licenze).
