import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Michele Pettinato
 */
public class Main {

    static final int TIME_SLOTS = 7, CLASSROOM_A_ID = 0, CLASSROOM_B_ID = 1, NUM_CLASSROOMS = 2, NO_CLASS = -1;
    static final float INFINITY = Integer.MAX_VALUE, SCORE_LIMIT = 1_000_000_000.0f;

    /* Dataset */

    static String[] salsaClasses = new String[] {
            "Salsa fusion by P.A. y Este",
            "Arm work salsa Fusion by Ilaria y Harshit",
            "Salsa all style by Caroline y Larissa",
            "Pasitos salsa con afro by Angelica Fino",
            "Lady Styling by Angelica Fino (man vs girl) / Man Styling by P.A. (man vs girl)"
    };
    static String[] bachataClasses = new String[] {
            "Bachata moderne by Patrick y Sofia",
            "Breaks and musicality partner work by Patrick",
            "Smooth transitions by Thanu y Sofia",
            "Sensual couple connection by Thanu y Larissa"
    };
    static String[] discoveryClasses = new String[] {
            "Reggaetton fusion by Ilaria",
            "Hip Hop by Ilaria",
            "Zouk Flow Introduction by Thanu y Julie",
            "Rumba by Angelica Fino"
    };

    static String[] participants = new String[] {
            "",
            "",
            "Ilaria Ricchi",
            "Angelica Fino",
            "Tessa Rougemont",
            "Matteo Mancuso",
            "Caroline Schmitt",
            "Léa Consuegra",
            "Nathalie Aney Guzmán Santa Cruz",
            "Richard Santiago",
            "Maja Stamenkovic",
            "Célina Marques",
            "Mariona Lopez GIl",
            "Ruth Díaz",
            "Thierry ",
            "Felix Hans Michel Grimberg ",
            "Balsiger Gloria ",
            "Sofia Echevarria",
            "Laurent Bugnard",
            "Igor Krawczuk ",
            "Léonore Baumann",
            "Matthieu Stigler",
            "Skander Moalla",
            "Miriam Pougin ",
            "Sonja Hirt",
            "Larissa Schuh",
            "Svitlana Mykolenko",
            "Emma Favrod",
            "Harshit Khurana",
            "Milena Schuhmacher ",
            "Florent GIFFON ",
            "Valentin Gobert",
            "Miguel Basante Bedoya ",
            "Thomas Stutz ",
            "Thanushan Kugathasan",
            "Patrick Sgrò ",
            "Michele Pettinato",
            "P.A.",
            "Estefania Arroyo",
            "Julie Hernandez ",
            "Sebastian Santos",
            "Alice Moraz",
            "Heidi Lee",
            "Hayoz Charly",
            "Tristan Reinhard"
    };

    // Note: I removed profs who voted for themselves to prioritize the classes they actually want to do
    static String[] participantsChoices = new String[] {
            "", // shift to match excel spreadsheet ids
            "", // shift to match excel spreadsheet ids
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Rumba by Angelica Fino",
            "", // angelica
            "Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Hip Hop by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Arm work salsa Fusion by Ilaria y Harshit,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino,Bachata moderne by Patrick y Sofia,Rumba by Angelica Fino",
            "Pasitos salsa con afro by Angelica Fino, Salsa fusion by P.A. y Este,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Smooth transitions by Thanu y Sofia,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Hip Hop by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Smooth transitions by Thanu y Sofia,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa",
            "Lady Styling by Angelica Fino (man vs girl), Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa",
            "Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit,Sensual couple connection by Thanu y Larissa",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Arm work salsa Fusion by Ilaria y Harshit,Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este,Bachata moderne by Patrick y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit,Breaks and musicality partner work by Patrick,Rumba by Angelica Fino",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Salsa all style by Caroline y Larissa,Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Lady Styling by Angelica Fino (man vs girl), Salsa all style by Caroline y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este,Sensual couple connection by Thanu y Larissa,Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl),Rumba by Angelica Fino",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Arm work salsa Fusion by Ilaria y Harshit,Breaks and musicality partner work by Patrick,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Breaks and musicality partner work by Patrick, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl),Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino,Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Arm work salsa Fusion by Ilaria y Harshit,Bachata moderne by Patrick y Sofia, Smooth transitions by Thanu y Sofia,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit,Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Breaks and musicality partner work by Patrick, Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl),Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl),Rumba by Angelica Fino",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este,Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "", // seb
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Lady Styling by Angelica Fino (man vs girl), Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Sensual couple connection by Thanu y Larissa,Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl),Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Salsa all style by Caroline y Larissa, Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa"
    };

    static int[] salsaVotes;
    static int[] bachataVotes;
    static int[] discoveryVotes;

    static int salsaStylingId = 4; // see salsaNames[salsaStylingId]

    public static void main(String[] args) {

        salsaVotes = new int[salsaClasses.length];
        bachataVotes = new int[bachataClasses.length];
        discoveryVotes = new int[discoveryClasses.length];

        /* People on duties (kitchen) */
        Set<Integer>[] duties = new Set[TIME_SLOTS];
        duties[0] = Set.of();
        duties[1] = Set.of(28, 42, 32, 44);
        duties[2] = Set.of();
        duties[3] = Set.of();
        duties[4] = Set.of(43, 7, 21, 18);
        duties[5] = Set.of();
        duties[6] = Set.of(4, 5, 8, 15);

        /* Teaching duties */
        List<Integer>[] salsaProfs = new List[salsaClasses.length],
                bachataProfs = new List[bachataClasses.length],
                discoveryProfs = new List[discoveryClasses.length];
        // see participantNames for the name of the profs
        final int PATRICK = 35;
        salsaProfs[0] = List.of(37, 38);
        salsaProfs[1] = List.of(2, 28);
        salsaProfs[2] = List.of(6, 25);
        salsaProfs[3] = List.of(3);
        salsaProfs[4] = List.of(3, 37);
        bachataProfs[0] = List.of(PATRICK, 17);
        bachataProfs[1] = List.of(PATRICK);
        bachataProfs[2] = List.of(34, 17);
        bachataProfs[3] = List.of(34, 25);
        discoveryProfs[0] = List.of(2);
        discoveryProfs[1] = List.of(2);
        discoveryProfs[2] = List.of(34, 39);
        discoveryProfs[3] = List.of(3);


        /* Hard constraint: Profs cannot teach when they are unavailable, like due to duties. (automated unless Patrick is not the masterchef anymore)
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
        int[] howManyChoices = new int[participantsChoices.length];
        for (int i = 0; i < salsaVoters.length; ++i) {
            salsaVoters[i] = new HashSet<>();
        }
        for (int i = 0; i < bachataVoters.length; ++i) {
            bachataVoters[i] = new HashSet<>();
        }
        for (int i = 0; i < discoveryVoters.length; ++i) {
            discoveryVoters[i] = new HashSet<>();
        }

        // assign voters to the classes they voted by matching classes names and choices
        for (int p = 0; p < participantsChoices.length; ++p) {
            for (String choice : participantsChoices[p].split(",")) {
                choice = choice.trim();
                if (choice.isEmpty()) {
                    continue;
                }
                ++howManyChoices[p];
                boolean classFound = false;
                // technical note: I use "contains" instead of equalsTo because stylingClassId merges Men and Lady Styling, so it's like two classes in one
                for (int c = 0; !classFound && c < salsaClasses.length; ++c) {
                    if (salsaClasses[c].contains(choice)) {
                        ++salsaVotes[c];
                        salsaVoters[c].add(p);
                        classFound = true;
                    }
                }
                for (int c = 0; !classFound && c < bachataClasses.length; ++c) {
                    if (bachataClasses[c].contains(choice)) {
                        ++bachataVotes[c];
                        bachataVoters[c].add(p);
                        classFound = true;
                    }
                }
                for (int c = 0; !classFound && c < discoveryClasses.length; ++c) {
                    if (discoveryClasses[c].contains(choice)) {
                        ++discoveryVotes[c];
                        discoveryVoters[c].add(p);
                        classFound = true;
                    }
                }
            }
        }

        /* Hard constraint:  profs cannot teach two classes at the same time (automated) */
        List<Integer>[] sameProfSalsaToBachata = new List[salsaVotes.length];
        List<Integer>[] sameProfSalsaToDiscovery = new List[salsaVotes.length];
        List<Integer>[] sameProfBachataToDiscovery = new List[bachataVotes.length];
        for (int s = 0; s < sameProfSalsaToBachata.length; ++s) {
            sameProfSalsaToBachata[s] = new ArrayList<>();
            sameProfSalsaToDiscovery[s] = new ArrayList<>();
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

        if ((salsaVotes.length + 1) + bachataVotes.length + discoveryVotes.length != NUM_CLASSROOMS * TIME_SLOTS) {
            throw new RuntimeException("Not Enough classes for the time slots");
        }
        int numClasses = salsaVotes.length + bachataVotes.length + discoveryVotes.length;
        System.out.println("--- Number of classes " + (numClasses + 1)); // +1 because of styiling counts as 2 classes


        /* Solve */
        Optimizer optimizer = new Optimizer(salsaVoters, bachataVoters, discoveryVoters, duties, howManyChoices, salsaProfs, bachataProfs, discoveryProfs,
                sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery,
                salsaProfsUnavailable, bachataProfsUnavailable, discoveryProfsUnavailable, salsaStylingId);
        float bestScore = optimizer.solve(0, (1 << numClasses) - 1, 2, 2, 2);
        System.out.println("--- Average Loss " + (bestScore / TIME_SLOTS));
        System.out.println();

        if (bestScore > SCORE_LIMIT) {
            System.out.println("Impossible !!");
            return;
        }

        /* Visualize */
        for (int i = 0, classesSubset = (1 << numClasses) - 1; i < TIME_SLOTS; ++i) {
            BestGuess bestGuess = optimizer.memo.get(classesSubset);
            System.out.print(printA(bestGuess));
            System.out.print(printB(bestGuess));
            int[] aVotes = bestGuess.isSalsaInA ? salsaVotes : bachataVotes;
            int[] bVotes = bestGuess.isBachataInB ? bachataVotes : discoveryVotes;
            System.out.println(optimizer.computeSimilarity(bestGuess.timeSlot, aVotes, bVotes) + "%");
            int aOffset = bestGuess.isSalsaInA ? (bachataVotes.length + discoveryVotes.length) : discoveryVotes.length;
            int bOffset = bestGuess.isBachataInB ? discoveryVotes.length : 0;
            classesSubset -= 1 << (aOffset + bestGuess.timeSlot[CLASSROOM_A_ID]);
            if (!bestGuess.isSalsaInA || bestGuess.timeSlot[CLASSROOM_A_ID] != salsaStylingId) {
                classesSubset -= 1 << (bOffset + bestGuess.timeSlot[CLASSROOM_B_ID]);
            }
        }

        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();
        int[] missingPreferences = new int[participants.length];
        for (int i = 0, classesSubset = (1 << numClasses) - 1; i < TIME_SLOTS; ++i) {
            BestGuess bestGuess = optimizer.memo.get(classesSubset);
            showResultPerPerson(bestGuess, salsaVoters, bachataVoters, discoveryVoters, salsaProfs, bachataProfs, discoveryProfs, duties, i, missingPreferences);
            int aOffset = bestGuess.isSalsaInA ? (bachataVotes.length + discoveryVotes.length) : discoveryVotes.length;
            int bOffset = bestGuess.isBachataInB ? discoveryVotes.length : 0;
            classesSubset -= 1 << (aOffset + bestGuess.timeSlot[CLASSROOM_A_ID]);
            if (!bestGuess.isSalsaInA || bestGuess.timeSlot[CLASSROOM_A_ID] != salsaStylingId) {
                classesSubset -= 1 << (bOffset + bestGuess.timeSlot[CLASSROOM_B_ID]);
            }
        }
        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();

        System.out.println("People that will have to do less than what they chose");
        Person[] result = new Person[participants.length];
        for (int p = 0; p < participants.length; ++p) {
            result[p] = new Person();
            result[p].name = participants[p];
            result[p].numPreferences = participantsChoices[p].split(",").length;
            result[p].missingPreferences = missingPreferences[p];
            result[p].score = missingPreferences[p] / (float) result[p].numPreferences;
        }
        Arrays.sort(result);
        for (Person person : result) {
            if (person.numPreferences - person.missingPreferences >= TIME_SLOTS || person.missingPreferences == 0) {
                continue;
            }
            System.out.println(person.name + "(" + person.numPreferences + "): " + (person.numPreferences - person.missingPreferences));
        }
    }

    static class Person implements Comparable<Person> {
        int numPreferences, missingPreferences;
        float score;
        String name;

        @Override
        public int compareTo(Person o) {
            return Float.compare(o.score, this.score);
        }
    }

    static void showResultPerPerson(BestGuess bestGuess, Set<Integer>[] salsa, Set<Integer>[] bachata, Set<Integer>[] discovery,
                                    List<Integer>[] salsaProfs, List<Integer>[] bachataProfs, List<Integer>[] discoveryProfs,
                                    Set<Integer>[] duties, int i, int[] missingPreferences) {
        Set<Integer>[] As = bestGuess.isSalsaInA ? salsa : bachata, Bs = bestGuess.isBachataInB ? bachata : discovery;
        Set<Integer> interestedInA = As[bestGuess.timeSlot[CLASSROOM_A_ID]];
        Set<Integer> interestedInBoth = new HashSet<>(), interestedInOneOrMore = new HashSet<>(interestedInA), onDutiesAndInterestedInOneOrMore = new HashSet<>(duties[i]);
        if (bestGuess.timeSlot[CLASSROOM_B_ID] != NO_CLASS) {
            Set<Integer> interestedInB =  Bs[bestGuess.timeSlot[CLASSROOM_B_ID]];
            interestedInBoth = new HashSet<>(interestedInA);
            interestedInBoth.retainAll(interestedInB);
            interestedInOneOrMore.addAll(interestedInB);
        }
        /* Count profs as on duty, because they have no option just like the people in the kitchen */
        List<Integer> profsA = bestGuess.isSalsaInA ? salsaProfs[bestGuess.timeSlot[CLASSROOM_A_ID]] : bachataProfs[bestGuess.timeSlot[CLASSROOM_A_ID]];
        List<Integer> profsB = new ArrayList<>();
        if (bestGuess.timeSlot[CLASSROOM_B_ID] != NO_CLASS) {
            profsB = !bestGuess.isBachataInB ? discoveryProfs[bestGuess.timeSlot[CLASSROOM_B_ID]] : bachataProfs[bestGuess.timeSlot[CLASSROOM_B_ID]];
        }
        onDutiesAndInterestedInOneOrMore.addAll(profsA);
        onDutiesAndInterestedInOneOrMore.addAll(profsB);

        onDutiesAndInterestedInOneOrMore.retainAll(interestedInOneOrMore);
        Set<Integer> onDutiesAndInterestedInBoth = new HashSet<>(interestedInBoth);
        onDutiesAndInterestedInBoth.retainAll(duties[i]);
        interestedInBoth.removeAll(onDutiesAndInterestedInBoth);
        onDutiesAndInterestedInOneOrMore.removeAll(onDutiesAndInterestedInBoth);
        System.out.println("WORKSHOP " + (i + 1) + ": List of people that wants to do both classes but miss one (" + interestedInBoth.size() + ")");
        for (int p : interestedInBoth) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p]++;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people on duty that miss one class they want to do (" + onDutiesAndInterestedInOneOrMore.size() + ")");
        for (int p : onDutiesAndInterestedInOneOrMore) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p]++;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people on duty missing BOTH classes they want to do (" + onDutiesAndInterestedInBoth.size() + ")");
        for (int p : onDutiesAndInterestedInBoth) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p] += 2;
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    static String printA(BestGuess bestGuess) {
        if (bestGuess.isSalsaInA) {
            return salsaClasses[bestGuess.timeSlot[CLASSROOM_A_ID]] + "(" + salsaVotes[bestGuess.timeSlot[CLASSROOM_A_ID]] +  ")" + "\t\t";
        }
        return bachataClasses[bestGuess.timeSlot[CLASSROOM_A_ID]] + "(" + bachataVotes[bestGuess.timeSlot[CLASSROOM_A_ID]] +  ")" + "\t\t";
    }

    static String printB(BestGuess bestGuess) {
        if (bestGuess.isSalsaInA && bestGuess.timeSlot[CLASSROOM_A_ID] == salsaStylingId) {
            return "";
        }
        if (bestGuess.isBachataInB) {
            return bachataClasses[bestGuess.timeSlot[CLASSROOM_B_ID]] + "(" + bachataVotes[bestGuess.timeSlot[CLASSROOM_B_ID]] +  ")" + "\t\t";
        }
        return discoveryClasses[bestGuess.timeSlot[CLASSROOM_B_ID]] + "(" + discoveryVotes[bestGuess.timeSlot[CLASSROOM_B_ID]] +  ")" + "\t\t";
    }

    private static class Optimizer {
        Map<Integer, BestGuess> memo = new HashMap<>();
        Set<Integer>[] salsa, bachata, discovery, duties;
        List<Integer>[] sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery;
        Set<Integer>[] salsaProfsUnavailable, bachataProfsUnavailable, discoveryProfsUnavailable;

        List<Integer>[] salsaProfs, bachataProfs, discoveryProfs;
        final BestGuess IMPOSSIBLE = new BestGuess(INFINITY);
        float[] pWeight;

        final int salsaStylingId;
        final float target;

        Optimizer(Set<Integer>[] salsa, Set<Integer>[] bachata, Set<Integer>[] discovery, Set<Integer>[] duties, int[] numPreferences,
                  List<Integer>[] salsaProfs,
                  List<Integer>[] bachataProfs,
                  List<Integer>[] discoveryProfs,
                  List<Integer>[] sameProfSalsaToBachata,
                  List<Integer>[] sameProfSalsaToDiscovery, List<Integer>[] sameProfBachataToDiscovery,
                  Set<Integer>[] salsaProfsUnavailable, Set<Integer>[] bachataProfsUnavailable, Set<Integer>[] discoveryProfsUnavailable,
                  int salsaStylingId) {
            this.salsa = salsa;
            this.bachata = bachata;
            this.discovery = discovery;
            this.duties = duties;
            this.salsaProfs = salsaProfs;
            this.bachataProfs = bachataProfs;
            this.discoveryProfs = discoveryProfs;
            this.sameProfSalsaToBachata = sameProfSalsaToBachata;
            this.sameProfSalsaToDiscovery = sameProfSalsaToDiscovery;
            this.sameProfBachataToDiscovery = sameProfBachataToDiscovery;
            this.salsaProfsUnavailable = salsaProfsUnavailable;
            this.bachataProfsUnavailable = bachataProfsUnavailable;
            this.discoveryProfsUnavailable = discoveryProfsUnavailable;
            this.salsaStylingId = salsaStylingId;
            pWeight = new float[numPreferences.length];
            for (int p = 0; p < numPreferences.length; ++p) {
                pWeight[p] = numPreferences[p] < 8 ? (8 - numPreferences[p]) : (-1.0f/6.0f * numPreferences[p] + 13.0f/6.0f);
            }
            this.target = (float) (Arrays.stream(salsaVotes).sum() + Arrays.stream(bachataVotes).sum() + Arrays.stream(discoveryVotes).sum())
                        / (salsaVotes.length + bachataVotes.length + discoveryVotes.length);
            System.out.println("--- Average " + target);
        }

        float solve(int timeSlotId, int classesSubset, int SB, int SD, int BD) {
            if (timeSlotId >= TIME_SLOTS) { // Base case
                boolean allClassesOnSchedule = classesSubset == 0;
                return allClassesOnSchedule ? 0.0f : INFINITY;
            }
            BestGuess ans = memo.get(classesSubset);
            if (ans != null) {
                return ans.score;
            }
            ans = guessA(timeSlotId, classesSubset, SB, SD, BD, new int[NUM_CLASSROOMS]);
            memo.put(classesSubset, ans);
            return ans.score;
        }

        BestGuess guessA(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot) {
            // guess Salsa in classroom A
            BestGuess bestGuess = guessA(timeSlotId, classesSubset, SB, SD, BD, timeSlot, bachata.length + discovery.length,
                    salsa.length, salsaProfsUnavailable, true, IMPOSSIBLE);
            // guess Bachata in classroom A
            return guessA(timeSlotId, classesSubset, SB, SD, BD, timeSlot, discovery.length,
                    bachata.length, bachataProfsUnavailable, false, bestGuess);
        }
        BestGuess guessA(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot,
                         int offset, int numClasses, Set<Integer>[] profsOnDuty, boolean isSalsaInA,
                         BestGuess bestGuess) {
            for (int currClass = 0; currClass < numClasses; ++currClass) {
                if (isProfUnavailable(currClass, timeSlotId, profsOnDuty)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked =  (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_A_ID] = currClass;
                    boolean isSalsaStyling = isSalsaInA && currClass == salsaStylingId;
                    BestGuess someGuess = guessB(timeSlotId, classesSubset - classMask, SB, SD, BD, timeSlot, isSalsaInA, isSalsaStyling);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_A_ID] = currClass;
                        someGuess.isSalsaInA = isSalsaInA;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess guessB(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot, boolean isSalsaInA, boolean isSalsaStyling) {
            if (isSalsaStyling) {
                timeSlot[CLASSROOM_B_ID] = NO_CLASS; // class A and B will be used for the styling
                return endOfTimeSlot(timeSlotId, classesSubset, SB, SD, BD, timeSlot, true, false, salsa, null);
            } else if (isSalsaInA) { // salsa in A, guess bachata or discovery in B
                BestGuess bestGuess = IMPOSSIBLE;
                if (SB > 0) {
                    bestGuess = guessB(timeSlotId, classesSubset, SB - 1, SD, BD, timeSlot,
                            discovery.length, salsa, bachata, true, true, bachataProfsUnavailable, sameProfSalsaToBachata, bestGuess);
                }
                if (SD > 0) {
                    bestGuess = guessB(timeSlotId, classesSubset, SB, SD - 1, BD, timeSlot,
                            0, salsa, discovery, true, false, discoveryProfsUnavailable, sameProfSalsaToDiscovery, bestGuess);
                }
                return bestGuess;
            } else { // bachata in A, only option is discovery for B
                if (BD <= 0) {
                    return IMPOSSIBLE;
                }
                return guessB(timeSlotId, classesSubset, SB, SD, BD - 1, timeSlot,
                        0, bachata, discovery, false, false, discoveryProfsUnavailable, sameProfBachataToDiscovery, IMPOSSIBLE);
            }
        }
        BestGuess guessB(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot,
                         int offset, Set<Integer>[] As, Set<Integer>[] Bs, boolean isSalsaInA, boolean isBachataInB,
                         Set<Integer>[] profsOnDuty, List<Integer>[] sameProfAtoB, BestGuess bestGuess) {
            for (int currClass = 0; currClass < Bs.length; ++currClass) {
                if (isProfAlreadyInTimeSlot(currClass, sameProfAtoB, timeSlot) || isProfUnavailable(currClass, timeSlotId, profsOnDuty)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked =  (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_B_ID] = currClass;
                    BestGuess someGuess = endOfTimeSlot(timeSlotId, classesSubset - classMask, SB, SD, BD, timeSlot, isSalsaInA, !isBachataInB, As, Bs);
                    if (someGuess.score < bestGuess.score || (someGuess.score == bestGuess.score && ThreadLocalRandom.current().nextFloat() < 0.5)) {
                        someGuess.timeSlot[CLASSROOM_B_ID] = currClass;
                        someGuess.isBachataInB = isBachataInB;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess endOfTimeSlot(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot, boolean isSalsaInA, boolean isDiscoveryInB, Set<Integer>[] As, Set<Integer>[] Bs) {
            float lookup = solve(timeSlotId + 1, classesSubset, SB, SD, BD);
            Set<Integer> B = timeSlot[CLASSROOM_B_ID] == NO_CLASS ? null : Bs[timeSlot[CLASSROOM_B_ID]];
            return lookup > SCORE_LIMIT ? IMPOSSIBLE : new BestGuess(
                    computeScore(timeSlotId, classesSubset, timeSlot, isSalsaInA, isDiscoveryInB, As[timeSlot[CLASSROOM_A_ID]], B) + lookup);
        }

        boolean isProfAlreadyInTimeSlot(int currClass, List<Integer>[] sameProfAtoB, int[] timeSlot) {
            return sameProfAtoB[timeSlot[CLASSROOM_A_ID]].stream().anyMatch(otherClassTheyGive -> currClass == otherClassTheyGive);
        }

        boolean isProfUnavailable(int currClass, int timeSlotId, Set<Integer>[] profsOnDuty) {
            return profsOnDuty[currClass].contains(timeSlotId);
        }

        private float computeAverage(int[] timeSlot, int[] aVotes, int[] bVotes) {
            if (timeSlot[CLASSROOM_B_ID] == NO_CLASS) {
                return aVotes[timeSlot[CLASSROOM_A_ID]];
            }
            float count = 2.0f;
            return (aVotes[timeSlot[CLASSROOM_A_ID]] + bVotes[timeSlot[CLASSROOM_B_ID]]) / count;
        }

        // implements loss = AB.count + [D(AuB)].count
        float computeScore(int timeSlotId, int classesSubset, int[] timeSlot, boolean isSalsaInA, boolean isDiscoveryInB, Set<Integer> interestedInA, Set<Integer> interestedInB) {
            Set<Integer> interestedInBoth = Set.of(), interestedInOneOrMore = new HashSet<>(interestedInA), onDutiesAndInterestedInOneOrMore = new HashSet<>(duties[timeSlotId]);
            if (interestedInB != null) {
                interestedInBoth = new HashSet<>(interestedInA);
                interestedInBoth.retainAll(interestedInB);
                interestedInOneOrMore.addAll(interestedInB);
            }
            /* Count profs as on duty, because they have no option just like the people in the kitchen */
            List<Integer> profsA = isSalsaInA ? salsaProfs[timeSlot[CLASSROOM_A_ID]] : bachataProfs[timeSlot[CLASSROOM_A_ID]];
            List<Integer> profsB = new ArrayList<>();
            if (timeSlot[CLASSROOM_B_ID] != NO_CLASS) {
                profsB = isDiscoveryInB ? discoveryProfs[timeSlot[CLASSROOM_B_ID]] : bachataProfs[timeSlot[CLASSROOM_B_ID]];
            }
            onDutiesAndInterestedInOneOrMore.addAll(profsA);
            onDutiesAndInterestedInOneOrMore.addAll(profsB);

            onDutiesAndInterestedInOneOrMore.retainAll(interestedInOneOrMore);
            final int RUMBA_CLASS = 3, REGGAE_CLASS = 0, STYLING = 4, STYLING_TIME = 4, PASITOS_ANGELICA = 3;
            float ANGELICA_W = 100.0f;
            boolean isAngelicaInA = (isSalsaInA && timeSlot[CLASSROOM_A_ID] == PASITOS_ANGELICA) ||
                    (isSalsaInA && timeSlot[CLASSROOM_A_ID] == STYLING);
            boolean isAngelicaInB = isDiscoveryInB && timeSlot[CLASSROOM_B_ID] == RUMBA_CLASS;

            float loss = interestedInBoth.stream().map(p -> pWeight[p]).reduce(0.0f, Float::sum);
            loss += onDutiesAndInterestedInOneOrMore.stream().map(p -> pWeight[p]).reduce(0.0f, Float::sum);
            if (isAngelicaInA) {
                Set<Integer> onDutiesAndInterestedInAngelica = new HashSet<>(onDutiesAndInterestedInOneOrMore);
                onDutiesAndInterestedInAngelica.retainAll(interestedInA);
                loss += onDutiesAndInterestedInAngelica.stream().map(p -> pWeight[p]).reduce(0.0f, Float::sum) * ANGELICA_W;
            } else if (isAngelicaInB) {
                Set<Integer> onDutiesAndInterstedInAngelica = new HashSet<>(onDutiesAndInterestedInOneOrMore);
                onDutiesAndInterstedInAngelica.retainAll(interestedInB);
                loss += onDutiesAndInterstedInAngelica.stream().map(p -> pWeight[p]).reduce(0.0f, Float::sum) * ANGELICA_W;
            }
            // Ilaria comes before Angelica
            if (isDiscoveryInB && timeSlot[CLASSROOM_B_ID] == RUMBA_CLASS) {
                int classMask = 1 << REGGAE_CLASS;
                if ((classesSubset & classMask) == 0) {
                    loss += 1000000;
                }
            }
            // Styling must be at a specific time
            if (isSalsaInA && timeSlot[CLASSROOM_A_ID] == STYLING && timeSlotId != STYLING_TIME) {
                loss += 100000;
            }

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

        float computeSimilarity(int[] timeSlot, int[] aVotes, int[] bVotes) {
            return computeAverage(timeSlot, aVotes, bVotes) / target * 100.0f;
        }
    }

    enum ClassLevel {
        INTERMEDIATE,
        ADVANCED
    }

    private static class BestGuess {
        float score;
        int[] timeSlot;
        boolean isSalsaInA, isBachataInB;
        BestGuess(float score) {
            this.score = score;
            timeSlot = new int[NUM_CLASSROOMS];
            timeSlot[0] = -1;
            timeSlot[1] = -1;
        }
    }
}
