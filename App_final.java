package SESTALEZIONE.EserciziPomeridiani; // Dichiarazione del package

import java.util.ArrayList; // Importazione della classe ArrayList
import java.util.Random; // Importazione della classe Random
import java.util.Scanner; // Importazione della classe Scanner

// Classe principale
public class App_final {
    private static final Scanner scanner = new Scanner(System.in); // Creazione di un oggetto Scanner per l'input da tastiera
    private static final ArrayList<Utente> utenti = new ArrayList<>(); // Creazione di una lista di utenti

    public static void main(String[] args) {
        while (true) { // Ciclo infinito per il menu principale
            System.out.println("\nMenu Principale"); // Stampa del menu principale
            System.out.println("1. Registrazione"); // Opzione di registrazione
            System.out.println("2. Login"); // Opzione di login
            System.out.println("3. Stampa utenti"); // Opzione per stampare gli utenti
            System.out.println("4. Modifica Nome Utente e Password"); // Opzione per modificare nome utente e password
            System.out.println("5. Esci"); // Opzione per uscire
            System.out.print("Scelta: "); // Richiesta di scelta

            int scelta = chiediNumero(); // Lettura della scelta dell'utente

            switch (scelta) { // Switch sulla scelta dell'utente
                case 1 -> Registrazione.registrazione(scanner, utenti); // Chiamata al metodo di registrazione
                case 2 -> Login.login(scanner, utenti); // Chiamata al metodo di login
                case 3 -> stampaUtenti(); // Chiamata al metodo per stampare gli utenti
                case 4 -> ModificaDatiUtente.modificaDatiUtente(scanner, utenti); // Chiamata al metodo per modificare i dati dell'utente
                case 5 -> { // Caso per uscire dal programma
                    System.out.println("Uscita dal programma..."); // Messaggio di uscita
                    scanner.close(); // Chiusura dello scanner
                    return; // Uscita dal metodo main
                }
                default -> System.out.println("Scelta non valida!"); // Messaggio di scelta non valida
            }
        }
    }

    private static void stampaUtenti() { // Metodo per stampare gli utenti
        utenti.forEach(Utente::stampaInfo); // Stampa delle informazioni di ogni utente
    }

    private static int chiediNumero() { // Metodo per chiedere un numero all'utente
        while (true) { // Ciclo infinito per la lettura del numero
            try {
                return Integer.parseInt(scanner.nextLine()); // Lettura e conversione del numero
            } catch (NumberFormatException e) { // Gestione dell'eccezione di formato non valido
                System.out.println("Inserisci un numero valido!"); // Messaggio di errore
            }
        }
    }
}

// Classe per la registrazione degli utenti
class Registrazione {
    public static void registrazione(Scanner scanner, ArrayList<Utente> utenti) { // Metodo per la registrazione
        System.out.print("Inserisci il nome utente: "); // Richiesta del nome utente
        String nome = scanner.nextLine(); // Lettura del nome utente
        System.out.print("Inserisci la password: "); // Richiesta della password
        String password = scanner.nextLine(); // Lettura della password
        utenti.add(new Utente(nome, password)); // Aggiunta del nuovo utente alla lista
        System.out.println("Registrazione riuscita!"); // Messaggio di registrazione riuscita
    }
}

// Classe per il login degli utenti
class Login {
    public static void login(Scanner scanner, ArrayList<Utente> utenti) { // Metodo per il login
        System.out.print("Inserisci il nome utente: "); // Richiesta del nome utente
        String nome = scanner.nextLine(); // Lettura del nome utente
        System.out.print("Inserisci la password: "); // Richiesta della password
        String password = scanner.nextLine(); // Lettura della password

        for (Utente utente : utenti) { // Ciclo per cercare l'utente nella lista
            if (utente.getNome().equals(nome) && utente.getPassword().equals(password)) { // Controllo delle credenziali
                System.out.println("Login riuscito! Benvenuto, " + nome); // Messaggio di login riuscito
                GiocoMatematico.gioca(utente); // Chiamata al metodo per giocare
                return; // Uscita dal metodo
            }
        }
        System.out.println("Credenziali errate!"); // Messaggio di credenziali errate
    }
}

// Classe per la modifica dei dati degli utenti
class ModificaDatiUtente {
    public static void modificaDatiUtente(Scanner scanner, ArrayList<Utente> utenti) { // Metodo per modificare i dati dell'utente
        System.out.print("Inserisci il nome utente attuale: "); // Richiesta del nome utente attuale
        String nome = scanner.nextLine(); // Lettura del nome utente attuale
        System.out.print("Inserisci la password: "); // Richiesta della password
        String password = scanner.nextLine(); // Lettura della password

        Utente utenteDaModificare = null; // Variabile per l'utente da modificare
        for (Utente utente : utenti) { // Ciclo per cercare l'utente nella lista
            if (utente.getNome().equals(nome) && utente.getPassword().equals(password)) { // Controllo delle credenziali
                utenteDaModificare = utente; // Assegnazione dell'utente da modificare
                break; // Uscita dal ciclo
            }
        }

        if (utenteDaModificare == null) { // Controllo se l'utente non è stato trovato
            System.out.println("Credenziali errate!"); // Messaggio di credenziali errate
            return; // Uscita dal metodo
        }

        System.out.print("Nuovo nome utente: "); // Richiesta del nuovo nome utente
        String nuovoNome = scanner.nextLine(); // Lettura del nuovo nome utente
        for (Utente utente : utenti) { // Ciclo per controllare se il nuovo nome utente è già in uso
            if (utente.getNome().equals(nuovoNome)) { // Controllo se il nome utente è già in uso
                System.out.println("Nome utente già in uso!"); // Messaggio di nome utente già in uso
                return; // Uscita dal metodo
            }
        }

        System.out.print("Nuova password: "); // Richiesta della nuova password
        String nuovaPassword = scanner.nextLine(); // Lettura della nuova password
        utenteDaModificare.setNome(nuovoNome); // Modifica del nome utente
        utenteDaModificare.setPassword(nuovaPassword); // Modifica della password
        System.out.println("Dati modificati con successo!"); // Messaggio di dati modificati con successo
    }
}

// Classe che rappresenta un punteggio dell'utente
class Punteggio {
    private int punti; // Variabile per i punti

    public Punteggio() { // Costruttore della classe Punteggio
        this.punti = 0; // Inizializzazione dei punti a 0
    }

    public int getPunti() { // Metodo per ottenere i punti
        return punti; // Ritorno dei punti
    }

    public void aggiornaPunti(int variazione) { // Metodo per aggiornare i punti
        this.punti += variazione; // Aggiornamento dei punti
    }

    public boolean isBloccato() { // Metodo per controllare se l'utente è bloccato
        return this.punti < -10; // Ritorno del risultato del controllo
    }
}

// Classe che rappresenta un utente del sistema
class Utente {
    private String nome; // Variabile per il nome
    private String password; // Variabile per la password
    private Punteggio punteggio; // Variabile per il punteggio

    public Utente(String nome, String password) { // Costruttore della classe Utente
        this.nome = nome; // Assegnazione del nome
        this.password = password; // Assegnazione della password
        this.punteggio = new Punteggio(); // Inizializzazione del punteggio
    }

    public String getNome() { // Metodo per ottenere il nome
        return nome; // Ritorno del nome
    }

    public String getPassword() { // Metodo per ottenere la password
        return password; // Ritorno della password
    }

    public int getPunti() { // Metodo per ottenere i punti
        return punteggio.getPunti(); // Ritorno dei punti
    }

    public boolean isBloccato() { // Metodo per controllare se l'utente è bloccato
        return punteggio.isBloccato(); // Ritorno del risultato del controllo
    }

    public void setNome(String nuovoNome) { // Metodo per impostare il nome
        this.nome = nuovoNome; // Assegnazione del nuovo nome
    }

    public void setPassword(String nuovaPassword) { // Metodo per impostare la password
        this.password = nuovaPassword; // Assegnazione della nuova password
    }

    public void aggiornaPunti(int variazione) { // Metodo per aggiornare i punti
        this.punteggio.aggiornaPunti(variazione); // Aggiornamento dei punti
    }

    public void stampaInfo() { // Metodo per stampare le informazioni dell'utente
        System.out.println("- " + nome + " | Punti: " + getPunti() + " | Bloccato: " + (isBloccato() ? "Sì" : "No")); // Stampa delle informazioni
    }
}

// Classe che rappresenta una domanda di matematica
class DomandaMatematica {
    private final int num1; // Variabile per il primo numero
    private int num2; // Variabile per il secondo numero
    private final String operazione; // Variabile per l'operazione
    private final int risultatoCorretto; // Variabile per il risultato corretto

    public DomandaMatematica(int livello) { // Costruttore della classe DomandaMatematica
        Random random = new Random(); // Creazione di un oggetto Random
        this.num1 = random.nextInt(10 * livello) + 1; // Generazione del primo numero
        this.num2 = random.nextInt(10 * livello) + 1; // Generazione del secondo numero

        switch (livello) { // Switch sul livello
            case 1 -> { // Caso per il livello 1
                this.operazione = "+"; // Assegnazione dell'operazione
                this.risultatoCorretto = num1 + num2; // Calcolo del risultato corretto
            }
            case 2 -> { // Caso per il livello 2
                this.operazione = "*"; // Assegnazione dell'operazione
                this.risultatoCorretto = num1 * num2; // Calcolo del risultato corretto
            }
            case 3 -> { // Caso per il livello 3
                this.operazione = "/"; // Assegnazione dell'operazione
                this.num2 = (num2 == 0) ? 1 : num2; // Controllo per evitare la divisione per zero
                this.risultatoCorretto = num1 / num2; // Calcolo del risultato corretto
            }
            default -> throw new IllegalStateException("Errore: Livello non valido!"); // Eccezione per livello non valido
        }
    }

    public boolean verificaRisposta(int risposta) { // Metodo per verificare la risposta
        return risposta == risultatoCorretto; // Ritorno del risultato della verifica
    }

    public String getDomanda() { // Metodo per ottenere la domanda
        return "Quanto fa " + num1 + " " + operazione + " " + num2 + "? "; // Ritorno della domanda
    }
}

// Classe che gestisce il gioco matematico
class GiocoMatematico {
    private static final Scanner scanner = new Scanner(System.in); // Creazione di un oggetto Scanner per l'input da tastiera
    private static final int[] punteggiMinimi = { 0, 20, 50 }; // Array dei punteggi minimi per i livelli

    public static void gioca(Utente utente) { // Metodo per giocare
        if (utente.isBloccato()) { // Controllo se l'utente è bloccato
            System.out.println("Sei stato bloccato e non puoi giocare!"); // Messaggio di blocco
            return; // Uscita dal metodo
        }

        int livello = 1; // Inizializzazione del livello
        while (livello < 4) { // Ciclo per i livelli
            if (utente.getPunti() < punteggiMinimi[livello - 1]) { // Controllo dei punti minimi per il livello
                System.out.println("Devi avere almeno " + punteggiMinimi[livello - 1]
                        + " punti per accedere al livello " + livello + "!"); // Messaggio di punti insufficienti
                break; // Uscita dal ciclo
            }

            System.out.println("\nLivello " + livello + " | Punti attuali: " + utente.getPunti()); // Stampa del livello e dei punti attuali
            DomandaMatematica domanda = new DomandaMatematica(livello); // Creazione di una nuova domanda
            System.out.print(domanda.getDomanda()); // Stampa della domanda
            int risposta = chiediNumero(""); // Lettura della risposta

            if (domanda.verificaRisposta(risposta)) { // Verifica della risposta
                utente.aggiornaPunti(10); // Aggiornamento dei punti in caso di risposta corretta
                System.out.println("Risposta corretta! +10 punti (Totale: " + utente.getPunti() + ")"); // Messaggio di risposta corretta
            } else {
                utente.aggiornaPunti(-5); // Aggiornamento dei punti in caso di risposta sbagliata
                System.out.println("Risposta sbagliata! -5 punti (Totale: " + utente.getPunti() + ")"); // Messaggio di risposta sbagliata
            }

            if (utente.isBloccato()) { // Controllo se l'utente è bloccato
                System.out.println("Hai perso troppi punti! Sei stato bloccato!"); // Messaggio di blocco
                return; // Uscita dal metodo
            }

            if (utente.getPunti() >= punteggiMinimi[livello]) { // Controllo se l'utente ha abbastanza punti per il livello successivo
                livello++; // Incremento del livello
            } else {
                System.out.println("Riprova per ottenere più punti e sbloccare il prossimo livello."); // Messaggio di riprova
            }
        }
    }

    private static int chiediNumero(String messaggio) { // Metodo per chiedere un numero all'utente
        while (true) { // Ciclo infinito per la lettura del numero
            try {
                System.out.print(messaggio); // Stampa del messaggio
                return Integer.parseInt(scanner.nextLine()); // Lettura e conversione del numero
            } catch (NumberFormatException e) { // Gestione dell'eccezione di formato non valido
                System.out.println("Inserisci un numero valido!"); // Messaggio di errore
            }
        }
    }
}

