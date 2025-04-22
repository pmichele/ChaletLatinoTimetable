import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Michele Pettinato
 */
public class Main {

    static final int TIME_SLOTS = 7, CLASSROOM_ONE_ID = 0, CLASSROOM_TWO_ID = 1, CLASSROOM_THREE_ID = 2, NUM_CLASSROOMS = 3, NO_CLASS = -1;
    static final float INFINITY = Integer.MAX_VALUE, SCORE_LIMIT = 1_000_000_000.0f;

    /* Dataset */

    static String[] salsaClasses = new String[]{
            "Salsa pasitos afro-rumba (Advanced)  —  Ila",
            "Salsa urban style (Advanced) — PA y Ila",
            "Salsa musicality: catching breaks (Intermediate) — Skander y Maja",
            "Salsa partnerwork (Intermediate) — Valentin y Lison",
            "Salsa fusion (Advanced) — PA y Este",
            "Salsa men vs lady styling battle (Intermediate) — PA y Este",
            "Salsa leading and following (Intermediate) — Robin y Marla",
            "Salsa partnerwork (Intermediate) — Robin y Kai"
    };
    static String[] bachataClasses = new String[]{
            "Bachata fusion (Advanced) — Lison y Patrick",
            "Bachata musicality parterwork (Intermediate) — Patrick",
            "Bachata sensual 1 (Intermediate) — Boris y Teya",
            "Bachata sensual 2 (Advanced) — Boris y Teya",
            "Bachata footwork (Intermediate) — Yoss y Morgana",
            "Bachata moderna (Intermediate) — Yoss y Morgana",
            "Bachata sensual connection (Advanced) — Yoss y Morgana"
    };
    static String[] discoveryClasses = new String[]{
            "Hip hop (All levels) — Ila",
            "Reggaeton (All levels) — Ila",
            "Jive (All levels) — Lison y Luca",
            "Samba (All levels) — Virginia",
            "Cha-Cha-Cha (All levels) — Heidi y Dennis",
            "Salsa caleña (All levels) — Luca"
    };
    //TODO

    static String[] participants = {
            "Adrian Pfiffner",
            "Marc Blöchlinger",
            "P.A.",
            "Léa Consuegra",
            "Julie Hernandez",
            "Mariana Leon",
            "Laurine Gasser",
            "Laurent Bugnard",
            "Noa Varela Cinquegrani",
            "Milena Schuhmacher",
            "Annamira O'Toole",
            "Ilaria Ricchi",
            "Kaisu Hiltunen",
            "Kai Ott",
            "Ruef Luca",
            "Lison Ravassard",
            "Larissa Klose",
            "Jose Antonio Simon Greminger",
            "Ivan Zivadinovic",
            "Skander Moalla",
            "Richard Santiago",
            "Michele Pettinato",
            "Yannick",
            "Jusef Akbari",
            "Chloé Monin",
            "Fabian Weber",
            "Elena Yfantis",
            "David Barioni",
            "Sabrina Kleesattel",
            "Carolin Giel",
            "Catarina Meier",
            "Maja Stamenkovic",
            "Alberto Zirondelli",
            "Céline Wilhelm",
            "Estefania Arroyo",
            "Daniela Correa Orozco",
            "Merel Kuijs",
            "Diego Clavijo",
            "Igor Krawczuk",
            "Heidi Lee"
    };


    static int[][] participantsSalsaChoices = {
            {2, 2, 2, 1, 1, 1, 1, 1},
            {0, 0, 2, 2, 0, 1, 2, 2},
            {1, 2, 0, 0, 2, 2, 0, 0},
            {2, 2, 2, 1, 2, 0, 1, 1},
            {0, 0, 1, 1, 1, 1, 1, 1},
            {2, 0, 2, 2, 1, 2, 1, 1},
            {0, 0, 1, 2, 0, 0, 1, 1},
            {2, 1, 2, 2, 0, 1, 1, 1},
            {1, 1, 1, 0, 2, 2, 0, 0},
            {2, 2, 1, 1, 1, 1, 1, 1},
            {0, 0, 2, 2, 0, 1, 2, 2},
            {2, 2, 1, 0, 2, 1, 0, 0},
            {0, 1, 1, 2, 1, 2, 1, 1},
            {2, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 2, 1, 2, 0, 1},
            {0, 0, 0, 2, 1, 2, 0, 0},
            {2, 0, 2, 1, 0, 0, 0, 1},
            {2, 0, 2, 1, 1, 2, 0, 0},
            {0, 0, 2, 2, 0, 1, 2, 2},
            {2, 1, 2, 1, 1, 2, 1, 1},
            {2, 1, 2, 2, 1, 1, 2, 2},
            {2, 2, 1, 0, 1, 2, 0, 0},
            {2, 0, 2, 1, 0, 1, 1, 1},
            {0, 0, 0, 1, 2, 0, 1, 2},
            {1, 0, 1, 1, 2, 2, 0, 1},
            {1, 0, 2, 0, 2, 0, 1, 2},
            {0, 0, 0, 1, 2, 0, 1, 2},
            {0, 1, 0, 1, 1, 2, 1, 1},
            {0, 0, 0, 1, 2, 0, 1, 2},
            {1, 1, 2, 2, 1, 1, 2, 1},
            {2, 2, 1, 1, 2, 1, 0, 0},
            {2, 2, 2, 1, 1, 1, 1, 1},
            {0, 0, 2, 2, 2, 1, 2, 2},
            {0, 0, 2, 2, 2, 1, 2, 2},
            {2, 2, 1, 0, 2, 2, 0, 0},
            {2, 2, 1, 0, 0, 1, 1, 1},
            {1, 1, 2, 1, 1, 0, 1, 2},
            {2, 1, 2, 2, 2, 1, 2, 2},
            {2, 2, 2, 1, 0, 0, 1, 0},
            {1, 1, 1, 1, 2, 2, 1, 1},
    };
    static int[][] participantsBachataChoices = {
            {2, 1, 2, 1, 1, 1, 0},
            {0, 1, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 2, 0, 0},
            {2, 1, 1, 1, 2, 2, 0},
            {1, 0, 2, 0, 1, 1, 0},
            {1, 2, 2, 0, 2, 1, 0},
            {0, 1, 2, 1, 2, 2, 0},
            {1, 2, 2, 1, 0, 1, 0},
            {1, 2, 0, 1, 2, 2, 0},
            {0, 2, 0, 0, 2, 1, 0},
            {1, 1, 0, 0, 0, 0, 0},
            {1, 1, 2, 2, 2, 2, 0},
            {2, 1, 2, 2, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 0},
            {2, 1, 1, 2, 0, 1, 0},
            {1, 2, 0, 0, 2, 0, 0},
            {1, 2, 2, 1, 2, 1, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 2, 2, 0, 1, 1, 0},
            {0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 2, 1, 0, 2, 0, 0},
            {2, 1, 2, 2, 1, 1, 0},
            {1, 1, 1, 2, 2, 1, 0},
            {0, 1, 2, 2, 1, 0, 0},
            {2, 1, 2, 2, 1, 1, 0},
            {0, 1, 1, 0, 1, 1, 0},
            {2, 1, 2, 2, 1, 1, 0},
            {1, 2, 2, 1, 2, 1, 0},
            {2, 1, 1, 1, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 2, 2, 0, 1, 1, 0},
            {0, 2, 2, 0, 1, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 2, 1, 1, 1, 1},
            {0, 1, 2, 2, 2, 1, 1},
            {0, 0, 2, 0, 0, 2, 1},
            {0, 2, 2, 0, 2, 1, 1},
            {1, 1, 2, 1, 0, 2, 1}
    };

    static int[][] participantsDiscoveryChoices = {
            {1, 1, 0, 0, 1, 1},
            {2, 2, 0, 0, 0, 1},
            {2, 1, 1, 0, 0, 0},
            {0, 2, 0, 1, 2, 2},
            {0, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 1, 1},
            {0, 1, 1, 0, 1, 0},
            {1, 1, 0, 1, 1, 0},
            {1, 2, 2, 1, 0, 0},
            {2, 2, 0, 1, 1, 2},
            {1, 1, 1, 1, 1, 1},
            {2, 2, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 2, 0, 1, 0, 0},
            {0, 0, 2, 0, 0, 2},
            {0, 0, 2, 0, 0, 1},
            {0, 0, 0, 2, 2, 1},
            {1, 2, 0, 2, 0, 2},
            {0, 2, 1, 1, 1, 2},
            {2, 2, 1, 0, 0, 0},
            {1, 1, 0, 0, 1, 1},
            {2, 2, 1, 2, 0, 2},
            {0, 1, 0, 2, 2, 2},
            {0, 2, 0, 1, 0, 0},
            {2, 2, 1, 2, 1, 1},
            {0, 1, 0, 1, 2, 0},
            {0, 2, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1},
            {0, 2, 0, 1, 1, 0},
            {1, 1, 1, 1, 1, 1},
            {1, 2, 1, 1, 1, 1},
            {1, 1, 2, 2, 2, 2},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {1, 1, 2, 2, 0, 1},
            {0, 1, 0, 1, 1, 1},
            {1, 1, 0, 1, 1, 2},
            {0, 0, 0, 0, 1, 1},
            {2, 2, 1, 2, 0, 1},
            {1, 1, 0, 1, 2, 1}
    };

    static int[] salsaVotes;
    static int[] bachataVotes;
    static int[] discoveryVotes;

    static int[] howManyChoices;

    static int salsaStylingId = 5; // see salsaClasses[salsaStylingId]

    public static void main(String[] args) {

        salsaVotes = new int[salsaClasses.length];
        bachataVotes = new int[bachataClasses.length];
        discoveryVotes = new int[discoveryClasses.length];

        /* People on duties (kitchen). They should be 5 on 1 4 and 6 */
        // TODO
        Set<Integer>[] duties = new Set[TIME_SLOTS];
        duties[0] = Set.of();
        duties[1] = Set.of(5, 6, 27);
        duties[2] = Set.of();
        duties[3] = Set.of();
        duties[4] = Set.of(3, 39);
        duties[5] = Set.of();
        duties[6] = Set.of(23, 28, 26, 52);

        /* Teaching duties */
        //TODO
        List<Integer>[] salsaProfs = new List[salsaClasses.length],
                bachataProfs = new List[bachataClasses.length],
                discoveryProfs = new List[discoveryClasses.length];
        // see participantNames for the name of the profs
        final int PATRICK = 33;
        salsaProfs[0] = List.of(11);
        salsaProfs[1] = List.of(11, 2);
        salsaProfs[2] = List.of(19, 31);
        salsaProfs[3] = List.of(43, 15);
        salsaProfs[4] = List.of(38, 2);
        salsaProfs[5] = List.of(38, 2);
        salsaProfs[6] = List.of(53, 54);
        salsaProfs[7] = List.of(53, 13);
        bachataProfs[0] = List.of(15, PATRICK);
        bachataProfs[1] = List.of(PATRICK);
        bachataProfs[2] = List.of(36, 39);
        bachataProfs[3] = List.of(36, 39);
        bachataProfs[4] = List.of(37, 45);
        bachataProfs[5] = List.of(37, 45);
        bachataProfs[6] = List.of(37, 45);
        discoveryProfs[0] = List.of(11);
        discoveryProfs[1] = List.of(11);
        discoveryProfs[2] = List.of(15, 14);
        discoveryProfs[3] = List.of();
        discoveryProfs[4] = List.of(50, 52);
        discoveryProfs[5] = List.of(14);

        /* Data cleaning : remove profs that voted for their own class BEFORE using the data */
        for (int s = 0; s < salsaProfs.length; ++s) {
            for (Integer prof : salsaProfs[s]) {
                participantsSalsaChoices[prof][s] = 0;
            }
        }
        for (int b = 0; b < bachataProfs.length; ++b) {
            for (Integer prof : bachataProfs[b]) {
                participantsBachataChoices[prof][b] = 0;
            }
        }
        for (int d = 0; d < discoveryProfs.length; ++d) {
            for (Integer prof : discoveryProfs[d]) {
                participantsDiscoveryChoices[prof][d] = 0;
            }
        }

        /* Hard constraint: Profs cannot teach when they are unavailable, like due to duties. (automated unless special case Patrick is not the masterchef anymore)
         * Note: these are not used for loss computation, so patrick does not get a boost in the loss, which is correct */
        Set<Integer>[] salsaProfsUnavailable = new Set[salsaClasses.length];
        Set<Integer>[] bachataProfsUnavailable = new Set[bachataClasses.length];
        Set<Integer>[] discoveryProfsUnavailable = new Set[discoveryClasses.length];
        for (int c = 0; c < salsaClasses.length; ++c) {
            salsaProfsUnavailable[c] = new HashSet<>();
            for (int d = 0; d < duties.length; ++d) {
                if (salsaProfs[c].stream().anyMatch(duties[d]::contains)) {
                    salsaProfsUnavailable[c].add(d);
                }
            }
//            System.out.println(salsaProfsUnavailable[c]);
        }
        for (int c = 0; c < bachataClasses.length; ++c) {
            bachataProfsUnavailable[c] = new HashSet<>();
            for (int d = 0; d < duties.length; ++d) {
                // ! SPECIAL CASE FOR PATRICK !
                boolean isKitchenTime = d == 1 || d == 4 || d == 6;
                boolean isPatrickSpecialCase = bachataProfs[c].contains(PATRICK) && isKitchenTime;
                if (isPatrickSpecialCase || bachataProfs[c].stream().anyMatch(duties[d]::contains)) {
                    bachataProfsUnavailable[c].add(d);
                }
            }
//            System.out.println(bachataProfsUnavailable[c]);
        }
        for (int c = 0; c < discoveryClasses.length; ++c) {
            discoveryProfsUnavailable[c] = new HashSet<>();
            for (int d = 0; d < duties.length; ++d) {
                if (discoveryProfs[c].stream().anyMatch(duties[d]::contains)) {
                    discoveryProfsUnavailable[c].add(d);
                }
            }
//            System.out.println(discoveryProfsUnavailable[c]);
        }

        /* Class to participants inverse map (automated) */
        Set<Integer>[] salsaVoters = new Set[salsaClasses.length];
        Set<Integer>[] bachataVoters = new Set[bachataClasses.length];
        Set<Integer>[] discoveryVoters = new Set[discoveryClasses.length];
        int numParticipants = participantsSalsaChoices.length;
        howManyChoices = new int[numParticipants];
        for (int i = 0; i < salsaVoters.length; ++i) {
            salsaVoters[i] = new HashSet<>();
        }
        for (int i = 0; i < bachataVoters.length; ++i) {
            bachataVoters[i] = new HashSet<>();
        }
        for (int i = 0; i < discoveryVoters.length; ++i) {
            discoveryVoters[i] = new HashSet<>();
        }

        // assign voters to the classes they voted
        for (int p = 0; p < numParticipants; ++p) {
            for (int c = 0; c < salsaClasses.length; ++c) {
                if (participantsSalsaChoices[p][c]) {
                    ++howManyChoices[p];
                    ++salsaVotes[c];
                    salsaVoters[c].add(p);
                }
            }
            for (int c = 0; c < bachataClasses.length; ++c) {
                if (participantsBachataChoices[p][c]) {
                    ++howManyChoices[p];
                    ++bachataVotes[c];
                    bachataVoters[c].add(p);
                }
            }
            for (int c = 0; c < discoveryClasses.length; ++c) {
                if (participantsDiscoveryChoices[p][c]) {
                    ++howManyChoices[p];
                    ++discoveryVotes[c];
                    discoveryVoters[c].add(p);
                }
            }
        }

        /* Hard constraint: profs cannot teach two classes at the same time (automated) */
        List<Integer>[] sameProfSalsaToBachata = new List[salsaVotes.length];
        List<Integer>[] sameProfSalsaToDiscovery = new List[salsaVotes.length];
        List<Integer>[] sameProfSalsaToSalsa = new List[salsaVotes.length];
        List<Integer>[] sameProfBachataToDiscovery = new List[bachataVotes.length];
        for (int s = 0; s < sameProfSalsaToSalsa.length; ++s) {
            sameProfSalsaToBachata[s] = new ArrayList<>();
            sameProfSalsaToDiscovery[s] = new ArrayList<>();
            sameProfSalsaToSalsa[s] = new ArrayList<>();
            for (int ss = 0; ss < salsaProfs.length; ++ss) {
                if (salsaProfs[s].stream().anyMatch(salsaProfs[ss]::contains)) {
                    sameProfSalsaToSalsa[s].add(ss);
                }
            }
            for (int b = 0; b < bachataProfs.length; ++b) {
                if (salsaProfs[s].stream().anyMatch(bachataProfs[b]::contains)) {
                    sameProfSalsaToBachata[s].add(b);
                }
            }
            for (int d = 0; d < discoveryProfs.length; ++d) {
                if (salsaProfs[s].stream().anyMatch(discoveryProfs[d]::contains)) {
                    sameProfSalsaToDiscovery[s].add(d);
                }
            }
//            System.out.println(sameProfSalsaToBachata[s]); // only one at a time
//            System.out.println(sameProfSalsaToDiscovery[s]); // only one at a time
        }
        for (int b = 0; b < sameProfBachataToDiscovery.length; ++b) {
            sameProfBachataToDiscovery[b] = new ArrayList<>();
            for (int d = 0; d < discoveryProfs.length; ++d) {
                if (bachataProfs[b].stream().anyMatch(discoveryProfs[d]::contains)) {
                    sameProfBachataToDiscovery[b].add(d);
                }
            }
//            System.out.println(sameProfBachataToDiscovery[b]);
        }

        if (salsaVotes.length + bachataVotes.length + discoveryVotes.length != NUM_CLASSROOMS * TIME_SLOTS) {
            throw new RuntimeException("Not Enough classes for the time slots");
        }
        int numClasses = salsaVotes.length + bachataVotes.length + discoveryVotes.length;
        System.out.println("--- Number of classes " + numClasses);


        /* Solve */
        Optimizer optimizer = new Optimizer(salsaVoters, bachataVoters, discoveryVoters, duties, howManyChoices, salsaProfs, bachataProfs, discoveryProfs,
                sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery, sameProfSalsaToSalsa,
                salsaProfsUnavailable, bachataProfsUnavailable, discoveryProfsUnavailable, salsaStylingId);
        float bestScore = optimizer.solve(0, (1 << numClasses) - 1, true);
        System.out.println("--- Average Loss " + (bestScore / TIME_SLOTS));
        System.out.println("--- with the current weight function this means how many classes missed per hour --");
        System.out.println();

        if (bestScore > SCORE_LIMIT) {
            System.out.println("Impossible !!");
            return;
        }

        /* Visualize */
        System.out.println("================== Weights ======================");
        for (int p = 0; p < participants.length; ++p) {
            System.out.println(participants[p] + " (" + howManyChoices[p] + "): " + optimizer.pWeight[p]);
        }
        for (int i = 0, classesSubset = (1 << numClasses) - 1; i < TIME_SLOTS; ++i) {
            BestGuess bestGuess = optimizer.memo.get(classesSubset);
            System.out.print(printA(bestGuess));
            System.out.print(printB(bestGuess));
            System.out.print(printC(bestGuess));
            System.out.println(optimizer.computeSimilarity(bestGuess) + "%");
            int salsaOffset = bachataVotes.length + discoveryVotes.length;
            int bachataOffset = discoveryVotes.length;
            classesSubset -= 1 << (salsaOffset + bestGuess.timeSlot[CLASSROOM_ONE_ID]);
            classesSubset -= 1 << (bachataOffset + bestGuess.timeSlot[CLASSROOM_TWO_ID]);
            if (bestGuess.hasClassThreeSalsa) {
                classesSubset -= 1 << (salsaOffset + bestGuess.timeSlot[CLASSROOM_THREE_ID]);
            } else {
                classesSubset -= 1 << (bestGuess.timeSlot[CLASSROOM_THREE_ID]);
            }
        }

        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();
        int[] missingPreferences = new int[participants.length];
        for (int i = 0, classesSubset = (1 << numClasses) - 1; i < TIME_SLOTS; ++i) {
            BestGuess bestGuess = optimizer.memo.get(classesSubset);
            showResultPerPerson(bestGuess, salsaVoters, bachataVoters, discoveryVoters, salsaProfs, bachataProfs, discoveryProfs, duties, i, missingPreferences);
            int salsaOffset = bachataVotes.length + discoveryVotes.length;
            int bachataOffset = discoveryVotes.length;
            classesSubset -= 1 << (salsaOffset + bestGuess.timeSlot[CLASSROOM_ONE_ID]);
            classesSubset -= 1 << (bachataOffset + bestGuess.timeSlot[CLASSROOM_TWO_ID]);
            if (bestGuess.hasClassThreeSalsa) {
                classesSubset -= 1 << (salsaOffset + bestGuess.timeSlot[CLASSROOM_THREE_ID]);
            } else {
                classesSubset -= 1 << (bestGuess.timeSlot[CLASSROOM_THREE_ID]);
            }
        }
        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();

        System.out.println("People that will have to do less than what they chose");
        Participant[] result = new Participant[participants.length];
        for (int p = 0; p < participants.length; ++p) {
            result[p] = new Participant();
            result[p].name = participants[p];
            result[p].numPreferences = howManyChoices[p];
            result[p].missingPreferences = missingPreferences[p];
            result[p].score = missingPreferences[p] / (float) result[p].numPreferences;
        }
        Arrays.sort(result);
        for (Participant participant : result) {
            if (participant.numPreferences - participant.missingPreferences >= TIME_SLOTS || participant.missingPreferences == 0) {
                continue;
            }
            System.out.println(participant.name + "(" + participant.numPreferences + "): " + (participant.numPreferences - participant.missingPreferences));
        }
    }

    static class Participant implements Comparable<Participant> {
        int numPreferences, missingPreferences;
        float score;
        String name;

        @Override
        public int compareTo(Participant o) {
            return Float.compare(o.score, this.score);
        }
    }

    static void showResultPerPerson(BestGuess bestGuess, Set<Integer>[] salsa, Set<Integer>[] bachata, Set<Integer>[] discovery,
                                    List<Integer>[] salsaProfs, List<Integer>[] bachataProfs, List<Integer>[] discoveryProfs,
                                    Set<Integer>[] duties, int i, int[] missingPreferences) {
        Set<Integer> interestedInSalsa = salsa[bestGuess.timeSlot[CLASSROOM_ONE_ID]];
        Set<Integer> interestedInBachata = bachata[bestGuess.timeSlot[CLASSROOM_TWO_ID]];
        Set<Integer> interestedInThirdClass = bestGuess.hasClassThreeSalsa ? salsa[bestGuess.timeSlot[CLASSROOM_THREE_ID]] : discovery[bestGuess.timeSlot[CLASSROOM_THREE_ID]];
        Set<Integer> interestedInSB = new HashSet<>(interestedInSalsa), interestedInST = new HashSet<>(interestedInSalsa), interestedInBT = new HashSet<>(interestedInBachata);
        interestedInSB.retainAll(interestedInBachata);
        interestedInST.retainAll(interestedInThirdClass);
        interestedInBT.retainAll(interestedInThirdClass);
        Set<Integer> interestedInExactlyThree = new HashSet<>(interestedInSalsa);
        interestedInExactlyThree.retainAll(interestedInBT);
        // Note : no need to remove SBT because it's included in SB, ST, BT
        Set<Integer> interestedInExactlyOne = new HashSet<>(interestedInSalsa);
        interestedInExactlyOne.addAll(interestedInBachata);
        interestedInExactlyOne.addAll(interestedInThirdClass);
        interestedInExactlyOne.removeAll(interestedInSB);
        interestedInExactlyOne.removeAll(interestedInST);
        interestedInExactlyOne.removeAll(interestedInBT);
        Set<Integer> interestedInExactlyTwo = new HashSet<>(interestedInSB);
        interestedInExactlyTwo.addAll(interestedInST);
        interestedInExactlyTwo.addAll(interestedInBT);
        interestedInExactlyTwo.removeAll(interestedInExactlyThree);
        Set<Integer> onDuty = new HashSet<>(duties[i]);
        /* Count profs as on duty, because they have no option just like the people in the kitchen */
        onDuty.addAll(salsaProfs[bestGuess.timeSlot[CLASSROOM_ONE_ID]]);
        onDuty.addAll(bachataProfs[bestGuess.timeSlot[CLASSROOM_TWO_ID]]);
        onDuty.addAll(bestGuess.hasClassThreeSalsa ? salsaProfs[bestGuess.timeSlot[CLASSROOM_THREE_ID]] : discoveryProfs[bestGuess.timeSlot[CLASSROOM_THREE_ID]]);

        Set<Integer> interestedInExactlyOneAndOnDuty = new HashSet<>(interestedInExactlyOne);
        interestedInExactlyOneAndOnDuty.retainAll(onDuty);
        Set<Integer> interestedInExactlyTwoAndOnDuty = new HashSet<>(interestedInExactlyTwo);
        interestedInExactlyTwoAndOnDuty.retainAll(onDuty);
        Set<Integer> interestedInExactlyThreeAndOnDuty = new HashSet<>(interestedInExactlyThree);
        interestedInExactlyThreeAndOnDuty.retainAll(onDuty);
        Set<Integer> interestedInExactlyTwoAndNotOnDuty = new HashSet<>(interestedInExactlyTwo);
        interestedInExactlyTwoAndNotOnDuty.removeAll(interestedInExactlyTwoAndOnDuty);
        Set<Integer> interestedInExactlyThreeAndNotOnDuty = new HashSet<>(interestedInExactlyThree);
        interestedInExactlyThreeAndNotOnDuty.removeAll(interestedInExactlyThreeAndOnDuty);

        System.out.println("WORKSHOP " + (i + 1) + ": List of people that will have to choose between two classes (" + interestedInExactlyTwoAndNotOnDuty.size() + ")");
        for (int p : interestedInExactlyTwoAndNotOnDuty) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p]++;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people that will have to choose between three classes (" + interestedInExactlyThreeAndNotOnDuty.size() + ")");
        for (int p : interestedInExactlyThreeAndNotOnDuty) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p] += 2;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people on duty that miss one class they want to do (" + interestedInExactlyOneAndOnDuty.size() + ")");
        for (int p : interestedInExactlyOneAndOnDuty) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p]++;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people on duty missing two classes they want to do (" + interestedInExactlyTwoAndOnDuty.size() + ")");
        for (int p : interestedInExactlyTwoAndOnDuty) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p] += 2;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people on duty missing three classes they want to do (" + interestedInExactlyThreeAndOnDuty.size() + ")");
        for (int p : interestedInExactlyThreeAndOnDuty) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p] += 3;
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    static String printA(BestGuess bestGuess) {
        return salsaClasses[bestGuess.timeSlot[CLASSROOM_ONE_ID]] + "(" + salsaVotes[bestGuess.timeSlot[CLASSROOM_ONE_ID]] + ")" + "\t\t";
    }

    static String printB(BestGuess bestGuess) {
        return bachataClasses[bestGuess.timeSlot[CLASSROOM_TWO_ID]] + "(" + bachataVotes[bestGuess.timeSlot[CLASSROOM_TWO_ID]] + ")" + "\t\t";
    }

    static String printC(BestGuess bestGuess) {
        if (bestGuess.hasClassThreeSalsa) {
            return salsaClasses[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + "(" + salsaVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + ")" + "\t\t";
        }
        return discoveryClasses[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + "(" + discoveryVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + ")" + "\t\t";

    }

    private static class Optimizer {
        Map<Integer, BestGuess> memo = new HashMap<>();
        Set<Integer>[] salsa, bachata, discovery, duties;
        List<Integer>[] sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery, sameProfSalsaToSalsa;
        Set<Integer>[] salsaProfsUnavailable, bachataProfsUnavailable, discoveryProfsUnavailable;

        List<Integer>[] salsaProfs, bachataProfs, discoveryProfs;
        final BestGuess IMPOSSIBLE = new BestGuess(INFINITY);
        float[] pWeight;

        final int salsaStylingId;
        final float heuristicTarget;

        Optimizer(Set<Integer>[] salsa, Set<Integer>[] bachata, Set<Integer>[] discovery, Set<Integer>[] duties, int[] numPreferences,
                  List<Integer>[] salsaProfs,
                  List<Integer>[] bachataProfs,
                  List<Integer>[] discoveryProfs,
                  List<Integer>[] sameProfSalsaToBachata,
                  List<Integer>[] sameProfSalsaToDiscovery, List<Integer>[] sameProfBachataToDiscovery,
                  List<Integer>[] sameProfSalsaToSalsa,
                  Set<Integer>[] salsaProfsUnavailable, Set<Integer>[] bachataProfsUnavailable, Set<Integer>[] discoveryProfsUnavailable,
                  int salsaStylingId) {
            this.salsa = salsa;
            this.bachata = bachata;
            this.discovery = discovery;
            this.duties = duties;
            this.salsaProfs = salsaProfs;
            this.bachataProfs = bachataProfs;
            this.discoveryProfs = discoveryProfs;
            this.sameProfSalsaToSalsa = sameProfSalsaToSalsa;
            this.sameProfSalsaToBachata = sameProfSalsaToBachata;
            this.sameProfSalsaToDiscovery = sameProfSalsaToDiscovery;
            this.sameProfBachataToDiscovery = sameProfBachataToDiscovery;
            this.salsaProfsUnavailable = salsaProfsUnavailable;
            this.bachataProfsUnavailable = bachataProfsUnavailable;
            this.discoveryProfsUnavailable = discoveryProfsUnavailable;
            this.salsaStylingId = salsaStylingId;
            pWeight = new float[numPreferences.length];
            for (int p = 0; p < numPreferences.length; ++p) {
                // method 1 : linear, high penalties for low choices
//                int CUTOFF = TIME_SLOTS + 1;
//                int MAX_COUNT = TIME_SLOTS * NUM_CLASSROOMS;
//                float m = -1.0f/(MAX_COUNT - TIME_SLOTS);
//                float q = 1.0f + TIME_SLOTS / (float) (MAX_COUNT - TIME_SLOTS);
//                pWeight[p] = numPreferences[p] < CUTOFF ? (CUTOFF - numPreferences[p]) : (m * numPreferences[p] + q);
                // method 2: hyperbolic, proportional so missing 1 / 2 is the same as 3.5 / 7
                pWeight[p] = numPreferences[p] == 0 ? 0.0f : TIME_SLOTS / (float) numPreferences[p];
            }
            this.heuristicTarget = (float) (Arrays.stream(salsaVotes).sum() + Arrays.stream(bachataVotes).sum() + Arrays.stream(discoveryVotes).sum())
                    / (salsaVotes.length + bachataVotes.length + discoveryVotes.length);
            initPenalties();
            System.out.println("--- Average " + heuristicTarget);
        }

        float solve(int timeSlotId, int classesSubset, boolean extraSalsa) {
            if (timeSlotId >= TIME_SLOTS) { // Base case
                boolean allClassesOnSchedule = classesSubset == 0;
                return allClassesOnSchedule ? 0.0f : INFINITY;
            }
            BestGuess ans = memo.get(classesSubset);
            if (ans != null) {
                return ans.score;
            }
            ans = guessSalsa(timeSlotId, classesSubset, extraSalsa, new int[NUM_CLASSROOMS]);
            memo.put(classesSubset, ans);
            return ans.score;
        }

        BestGuess guessSalsa(int timeSlotId, int classesSubset, boolean extraSalsa, int[] timeSlot) {
            BestGuess bestGuess = IMPOSSIBLE;
            int offset = bachata.length + discovery.length;
            for (int currClass = 0; currClass < salsa.length; ++currClass) {
                if (isProfUnavailable(currClass, timeSlotId, salsaProfsUnavailable)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked = (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_ONE_ID] = currClass;
                    BestGuess someGuess = guessBachata(timeSlotId, classesSubset - classMask, extraSalsa, timeSlot);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_ONE_ID] = currClass;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess guessBachata(int timeSlotId, int classesSubset, boolean extraSalsa, int[] timeSlot) {
            BestGuess bestGuess = IMPOSSIBLE;
            int offset = discovery.length;
            for (int currClass = 0; currClass < bachata.length; ++currClass) {
                if (isProfAlreadyInTimeSlot(currClass, sameProfSalsaToBachata, timeSlot[CLASSROOM_ONE_ID])
                        || isProfUnavailable(currClass, timeSlotId, bachataProfsUnavailable)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked = (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_TWO_ID] = currClass;
                    BestGuess someGuess = guessDiscovery(timeSlotId, classesSubset - classMask, extraSalsa, timeSlot);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_TWO_ID] = currClass;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess guessDiscovery(int timeSlotId, int classesSubset, boolean extraSalsa, int[] timeSlot) {
            BestGuess bestGuess = IMPOSSIBLE;
            if (extraSalsa) {
                int offset = bachata.length + discovery.length;
                for (int currClass = 0; currClass < salsa.length; ++currClass) {
                    if (isProfAlreadyInTimeSlot(currClass, sameProfSalsaToSalsa, timeSlot[CLASSROOM_ONE_ID])
                            || isProfAlreadyInTimeSlot(timeSlot[CLASSROOM_TWO_ID], sameProfSalsaToBachata, currClass)
                            || isProfUnavailable(currClass, timeSlotId, salsaProfsUnavailable)) {
                        continue;
                    }
                    int classMask = 1 << (offset + currClass);
                    boolean isClassNotPicked = (classesSubset & classMask) > 0;
                    if (isClassNotPicked) {
                        timeSlot[CLASSROOM_THREE_ID] = currClass;
                        BestGuess someGuess = endOfTimeSlot(timeSlotId, classesSubset - classMask, false, true, timeSlot);
                        if (someGuess.score < bestGuess.score) {
                            someGuess.timeSlot[CLASSROOM_THREE_ID] = currClass;
                            someGuess.hasClassThreeSalsa = true;
                            bestGuess = someGuess;
                        }
                    }
                }
            }
            int offset = 0;
            for (int currClass = 0; currClass < discovery.length; ++currClass) {
                if (isProfAlreadyInTimeSlot(currClass, sameProfSalsaToDiscovery, timeSlot[CLASSROOM_ONE_ID])
                        || isProfAlreadyInTimeSlot(currClass, sameProfBachataToDiscovery, timeSlot[CLASSROOM_TWO_ID])
                        || isProfUnavailable(currClass, timeSlotId, bachataProfsUnavailable)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked = (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_THREE_ID] = currClass;
                    BestGuess someGuess = endOfTimeSlot(timeSlotId, classesSubset - classMask, extraSalsa, false, timeSlot);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_THREE_ID] = currClass;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess endOfTimeSlot(int timeSlotId, int classesSubset, boolean extraSalsa, boolean hasClassThreeSalsa, int[] timeSlot) {
            float lookup = solve(timeSlotId + 1, classesSubset, extraSalsa);
            float loss = computeScore(timeSlotId, classesSubset, timeSlot, hasClassThreeSalsa);
            return lookup > SCORE_LIMIT ? IMPOSSIBLE : new BestGuess(loss + lookup);
        }

        boolean isProfAlreadyInTimeSlot(int b, List<Integer>[] sameProfAtoB, int a) {
            return sameProfAtoB[a].stream().anyMatch(otherClassTheyGive -> b == otherClassTheyGive);
        }

        boolean isProfUnavailable(int currClass, int timeSlotId, Set<Integer>[] profsOnDuty) {
            return profsOnDuty[currClass].contains(timeSlotId);
        }

        private float computeAverage(int[] timeSlot, int[] aVotes, int[] bVotes, int[] cVotes) {
            float count = 3.0f;
            return (aVotes[timeSlot[CLASSROOM_ONE_ID]] + bVotes[timeSlot[CLASSROOM_TWO_ID]] + cVotes[timeSlot[CLASSROOM_THREE_ID]]) / count;
        }

        float weightedSum(Set<Integer> A) {
            return A.stream().map(p -> pWeight[p]).reduce(0.0f, Float::sum);
        }

        float[][][][] salsaPenalty, penalty;

        // implements loss = |A| + |B| + |C| - |AuBuC| + |D(AuBuC)|
        void initPenalties(Set<Integer>[] thirdClass, List<Integer>[] thirdClassProfs, float[][][][] result) {
            for (int s = 0; s < salsa.length; ++s) {
                for (int b = 0; b < bachata.length; ++b) {
                    for (int d = 0; d < thirdClass.length; ++d) {
                        Set<Integer> AuBuC = new HashSet<>(salsa[s]);
                        AuBuC.addAll(bachata[b]);
                        AuBuC.addAll(thirdClass[d]);
                        for (int i = 0; i < TIME_SLOTS; ++i) {
                            Set<Integer> DAuBuC = new HashSet<>(duties[i]);
                            /* Count profs as on duty, because they have no option just like the people in the kitchen */
                            DAuBuC.addAll(salsaProfs[s]);
                            DAuBuC.addAll(bachataProfs[b]);
                            DAuBuC.addAll(thirdClassProfs[d]);
                            DAuBuC.retainAll(AuBuC);
                            result[i][s][b][d] = weightedSum(salsa[s]) + weightedSum(bachata[b]) + weightedSum(thirdClass[d])
                                    - weightedSum(AuBuC) + weightedSum(DAuBuC);
                        }
                    }
                }
            }
        }

        void initPenalties() {
            System.out.println("--------- Init penalties ------------");
            penalty = new float[TIME_SLOTS][salsa.length][bachata.length][discovery.length];
            salsaPenalty = new float[TIME_SLOTS][salsa.length][bachata.length][salsa.length];
            initPenalties(discovery, discoveryProfs, penalty);
            System.out.println("--------- Init salsa penalties ------------");
            initPenalties(salsa, salsaProfs, salsaPenalty);
            System.out.println("--------- Init penalties done ------------");

        }

        float computeScore(int timeSlotId, int classesSubset, int[] timeSlot, boolean hasClassThreeSalsa) {
            float loss = hasClassThreeSalsa ? salsaPenalty[timeSlotId][timeSlot[CLASSROOM_ONE_ID]][timeSlot[CLASSROOM_TWO_ID]][timeSlot[CLASSROOM_THREE_ID]]
                    : penalty[timeSlotId][timeSlot[CLASSROOM_ONE_ID]][timeSlot[CLASSROOM_TWO_ID]][timeSlot[CLASSROOM_THREE_ID]];
            // Styling must be at a specific time
//            if (timeSlot[CLASSROOM_ONE_ID] == STYLING && timeSlotId != STYLING_TIME) {
//                loss += 100000;
//            }

            /* Hyperparameters for soft constraints*/
            /* Below are some examples that are reasonable to fine tune happiness of the people attending the workshops.
                Note: in practice the weights used are irrelevant as all constraints could be satisfied
              if (isBothSensual) {
                  meanAbsoluteError += Integer.MAX_VALUE;
              }
              if (isPAPartnerwork && isRetoPartnerwork) {
                  meanAbsoluteError += 800.0f;
              }
              // ensure that difficulty is alternating across time slots
              if (timeSlot[EXTRA_SALSA_TYPE_ID] < 0 &&
                      timeSlot[EXTRA_BACHATA_TYPE_ID] < 0 &&
                      (isTimeAdvanced && salsaClassLevels[timeSlot[SALSA_TYPE_ID]] == ClassLevel.INTERMEDIATE)
                  ||  (isTimeIntermediate && salsaClassLevels[timeSlot[SALSA_TYPE_ID]] == ClassLevel.ADVANCED)) {
                  meanAbsoluteError += 1000.0f;
              }
              if (isTimeAfterLunch && (isCalena || isHipHop) ) {
                  meanAbsoluteError += Integer.MAX_VALUE;
              }
            */
            return loss;
        }

        float computeSimilarity(BestGuess bestGuess) {
            int[] cVotes = bestGuess.hasClassThreeSalsa ? salsaVotes : discoveryVotes;
            return computeAverage(bestGuess.timeSlot, salsaVotes, bachataVotes, cVotes) / heuristicTarget * 100.0f;
        }
    }

    enum ClassLevel {
        INTERMEDIATE,
        ADVANCED
    }

    private static class BestGuess {
        float score;
        int[] timeSlot;
        boolean hasClassThreeSalsa;

        BestGuess(float score) {
            this.score = score;
            timeSlot = new int[NUM_CLASSROOMS];
            for (int i = 0; i < NUM_CLASSROOMS; ++i) {
                timeSlot[i] = -1;
            }
        }
    }
}
