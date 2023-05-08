import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Michele Pettinato
 */
public class Main {

    static final int TIME_SLOTS = 8, SALSA_TYPE_ID = 0, BACHATA_TYPE_ID = 2, DISCOVERY_TYPE_ID = 4,
            EXTRA_SALSA_TYPE_ID = 1, EXTRA_BACHATA_TYPE_ID = 3, NUM_DANCE_TYPES = 5, END_OF_TIME_SLOT = NUM_DANCE_TYPES, NO_CLASS = -1;
    static final float INFINITY = Integer.MAX_VALUE, SCORE_LIMIT = 1_000_000_000.0f;

    public static void main(String[] args) {
        /* Data Set. TODO: load from an external configuration file */
        String[] salsaNames = new String[] {
                "Partnerwork con rumba with PA y Este (Advanced)",
                "Partnerwork con urban flow with PA y Paula (Advanced)",
                "Lady/Man styling with Este, Ilaria y Kevin (Inter)",
                "Fusion con reggaeton with Ilaria (Inter)",
                "Rueda with Caroline (Advanced)",
                "All style with Caroline (Inter)",
                "Son cubano with Stefan y Tanja (Advanced)",
                "Partner stealing and interchange with Stefan y Larissa (Advanced)",
                "Partnerwork with Reto (Inter)"
        };
        String[] bachataNames = new String[] {
                "Moderna with Patrick y Silvia (Inter)",
                "Sensual with Patrick y Paula (Advanced)",
                "Connection and zouk fusion with Kevin y Chelsea (Advanced)",
                "Subtle leading tips with Anli y Chelsea (Advanced)",
                "Smooth partnerwork with Anli y Chelsea (Inter)",
                "Partnerwork triple fun with Anli y Aurora (Inter)",
                "Sensual with Michael y Silvia (Inter)",
                "Fusion dynamic flow with Thanu y Tessa (Inter)",
                "Dips and falls with Lorenz y Paula (Advanced)"
        };
        String[] discoveryNames = new String[] {
                "Kompa \"original zouk\" with PA y Este",
                "Hip Hop in commercial music with Ilaria",
                "Arm games in LA style salsa with Kevin y Camille",
                "Salsa LA style with Lionel y Julie",
                "Kizomba with Michael y Silvia",
                "Dominican bachata with Lionel y Tanja",
                "Salsa caleña with Reto y Solène",
                "Brazilian Zouk with Julian y Robin",
        };
//      arbitrary
//        int[] salsaVotes = new int[] {10, 20, 35, 10, 28, 30, 32, 34, 36};
//        int[] bachataVotes = new int[] {38, 40, 42, 44, 46, 48, 50, 52, 54};
//        int[] discoveryVotes = new int[] {56, 58, 60, 62, 64, 66, 68, 70};
//      random
//        int[] salsaVotes = new int[] {32, 27, 33, 26, 6, 31, 69, 10, 55};
//        int[] bachataVotes = new int[] {23, 62, 38, 47, 18, 67, 52, 34, 56};
//        int[] discoveryVotes = new int[] {2, 30, 20, 10, 59, 53, 56, 60};
//      final
        int[] salsaVotes = new int[] {35, 36, 51, 34, 34, 20, 31, 30, 28};
        int[] bachataVotes = new int[] {21, 33, 17, 21, 25, 20, 28, 23, 28};
        int[] discoveryVotes = new int[] {19, 29, 26, 36, 31, 35, 37, 32};
        int salsaStylingId = 2;

        // 1. no same prof in parallel constraints
        List<Integer>[] sameProfSalsaToBachata = new List[salsaVotes.length];
        List<Integer>[] sameProfSalsaToDiscovery = new List[salsaVotes.length];
        List<Integer>[] sameProfSalsaToSalsa = new List[salsaVotes.length];
        List<Integer>[] sameProfBachataToDiscovery = new List[bachataVotes.length];
        List<Integer>[] sameProfBachataToBachata = new List[bachataVotes.length];
        for (int i = 0; i < sameProfSalsaToBachata.length; ++i) {
            sameProfSalsaToBachata[i] = new ArrayList<>();
            sameProfSalsaToDiscovery[i] = new ArrayList<>();
            sameProfSalsaToSalsa[i] = new ArrayList<>();
        }
        for (int i = 0; i < sameProfBachataToDiscovery.length; ++i) {
            sameProfBachataToDiscovery[i] = new ArrayList<>();
            sameProfBachataToBachata[i] = new ArrayList<>();
        }
        sameProfSalsaToBachata[1] = Arrays.asList(1, 8); // PA & Paula
        sameProfSalsaToBachata[2] = Arrays.asList(2); // Kevin man styling
        sameProfSalsaToSalsa[0] = Arrays.asList(1, 2); // PA & Estef
        sameProfSalsaToSalsa[1] = Arrays.asList(0); // PA & Paula
        sameProfSalsaToSalsa[2] = Arrays.asList(0, 3); // Estef & Ilaria, Kevin
        sameProfSalsaToSalsa[3] = Arrays.asList(2); // Ilaria
        sameProfSalsaToSalsa[4] = Arrays.asList(5); // Caroline
        sameProfSalsaToSalsa[5] = Arrays.asList(4); // Caroline
        sameProfSalsaToSalsa[6] = Arrays.asList(7); // Stefan & Tanja
        sameProfSalsaToSalsa[7] = Arrays.asList(6); // Stefan & Larissa
        sameProfSalsaToDiscovery[0] = Arrays.asList(0); // PA & Estef
        sameProfSalsaToDiscovery[1] = Arrays.asList(0); // PA & Paula
        sameProfSalsaToDiscovery[2] = Arrays.asList(0, 1, 2); // Estef, Ilaria & Kevin styling
        sameProfSalsaToDiscovery[3] = Arrays.asList(1); // Ilaria
        sameProfSalsaToDiscovery[6] = Arrays.asList(5); // Tanja
        sameProfSalsaToDiscovery[8] = Arrays.asList(6); // Reto
        sameProfBachataToDiscovery[0] = Arrays.asList(4); // Patrick & Silvia
        sameProfBachataToDiscovery[2] = Arrays.asList(2); // Kevin & Chelsea
        sameProfBachataToDiscovery[6] = Arrays.asList(4); // Michael & Silvia
        sameProfBachataToBachata[0] = Arrays.asList(1, 6); // Patrick & Silvia
        sameProfBachataToBachata[1] = Arrays.asList(0, 8); // Patrick & Paula
        sameProfBachataToBachata[2] = Arrays.asList(3, 4); // Kevin & Chelsea
        sameProfBachataToBachata[3] = Arrays.asList(2, 4, 5); // Anli & Chelsea
        sameProfBachataToBachata[4] = Arrays.asList(2, 3, 5) ;// Anli & Chelsea
        sameProfBachataToBachata[5] = Arrays.asList(3, 4); // Anli & Aurora
        sameProfBachataToBachata[6] = Arrays.asList(0); // Michael & Silvia
        sameProfBachataToBachata[8] = Arrays.asList(1); // Lorenz & Paula


        // 2. Profs on duties constraints
        List<Integer>[] salsaProfsOnDuty = new List[salsaVotes.length];
        List<Integer>[] bachataProfsOnDuty = new List[bachataVotes.length];
        List<Integer>[] discoveryProfsOnDuty = new List[discoveryVotes.length];
        for (int i = 0; i < salsaProfsOnDuty.length; ++i) {
            salsaProfsOnDuty[i] = new ArrayList<>();
        }
        for (int i = 0; i < bachataProfsOnDuty.length; ++i) {
            bachataProfsOnDuty[i] = new ArrayList<>();
        }
        for (int i = 0; i < discoveryProfsOnDuty.length; ++i) {
            discoveryProfsOnDuty[i] = new ArrayList<>();
        }
        salsaProfsOnDuty[6] = Arrays.asList(6, 7); // Stefan
        salsaProfsOnDuty[7] = Arrays.asList(1, 6, 7); // Stefan & Larissa
        bachataProfsOnDuty[0] = Arrays.asList(1, 5, 7); // Patrick & Silvia
        bachataProfsOnDuty[1] = Arrays.asList(1, 5, 7); // Patrick & Paula
        bachataProfsOnDuty[5] = Arrays.asList(5); // Anli & Aurora
        bachataProfsOnDuty[7] = Arrays.asList(5); // Thanu & Tessa
        bachataProfsOnDuty[8] = Arrays.asList(7); // Lorenz & Paula

        // 3. no same class level in parallel (2 advanced or 2 intermediates)
        ClassLevel[] salsaClassLevels = new ClassLevel[] {
                ClassLevel.ADVANCED,
                ClassLevel.ADVANCED,
                ClassLevel.INTERMEDIATE,
                ClassLevel.INTERMEDIATE,
                ClassLevel.ADVANCED,
                ClassLevel.INTERMEDIATE,
                ClassLevel.ADVANCED,
                ClassLevel.ADVANCED,
                ClassLevel.INTERMEDIATE
        };
        ClassLevel[] bachataClassLevels = new ClassLevel[] {
                ClassLevel.INTERMEDIATE,
                ClassLevel.ADVANCED,
                ClassLevel.ADVANCED,
                ClassLevel.ADVANCED,
                ClassLevel.INTERMEDIATE,
                ClassLevel.INTERMEDIATE,
                ClassLevel.INTERMEDIATE,
                ClassLevel.INTERMEDIATE,
                ClassLevel.ADVANCED
        };
        if (salsaVotes.length < TIME_SLOTS || bachataVotes.length < TIME_SLOTS || discoveryVotes.length < TIME_SLOTS) {
            throw new RuntimeException("Not Enough classes for the time slots");
        }
        int numClasses = salsaVotes.length + bachataVotes.length + discoveryVotes.length;
        System.out.println("--- Number of classes " + numClasses);

        /* Solve */
        Optimizer optimizer = new Optimizer(salsaVotes, bachataVotes, discoveryVotes,
                sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery,
                sameProfSalsaToSalsa, sameProfBachataToBachata,
                salsaProfsOnDuty, bachataProfsOnDuty, discoveryProfsOnDuty,
                salsaClassLevels, bachataClassLevels, salsaStylingId);
        float bestScore = optimizer.solve(0, (1 << numClasses) - 1, false, false);
        System.out.println("--- Average loss " + (bestScore / TIME_SLOTS / optimizer.target * 100.0f) + "%");
        System.out.println();

        if (bestScore > SCORE_LIMIT) {
            System.out.println("Impossible !!");
            return;
        }

        /* Visualize */
        for (int i = 0, classesSubset = (1 << numClasses) - 1; i < TIME_SLOTS; ++i) {
            BestGuess bestGuess = optimizer.memo.get(classesSubset);
            System.out.print(salsaNames[bestGuess.timeSlot[SALSA_TYPE_ID]] + "(" + salsaVotes[bestGuess.timeSlot[SALSA_TYPE_ID]] +  ")" + "\t\t");
            System.out.print(bachataNames[bestGuess.timeSlot[BACHATA_TYPE_ID]] + "(" + bachataVotes[bestGuess.timeSlot[BACHATA_TYPE_ID]] +  ")" + "\t\t");
            System.out.print(discoveryNames[bestGuess.timeSlot[DISCOVERY_TYPE_ID]] + "(" + discoveryVotes[bestGuess.timeSlot[DISCOVERY_TYPE_ID]] +  ")" + "\t\t");
            if (bestGuess.timeSlot[EXTRA_SALSA_TYPE_ID] >= 0) {
                System.out.print(salsaNames[bestGuess.timeSlot[EXTRA_SALSA_TYPE_ID]] + "(" + salsaVotes[bestGuess.timeSlot[EXTRA_SALSA_TYPE_ID]] +  ")" + "\t\t");
                classesSubset -= 1 << (bachataVotes.length + discoveryVotes.length + bestGuess.timeSlot[EXTRA_SALSA_TYPE_ID]);
            } else if (bestGuess.timeSlot[EXTRA_BACHATA_TYPE_ID] >= 0) {
                System.out.print(bachataNames[bestGuess.timeSlot[EXTRA_BACHATA_TYPE_ID]] + "(" + bachataVotes[bestGuess.timeSlot[EXTRA_BACHATA_TYPE_ID]] +  ")" + "\t\t");
                classesSubset -= 1 << (discoveryVotes.length + bestGuess.timeSlot[EXTRA_BACHATA_TYPE_ID]);
            }
            System.out.println(optimizer.computeSimilarity(bestGuess.timeSlot) + "%");
            classesSubset -= 1 << (bachataVotes.length + discoveryVotes.length + bestGuess.timeSlot[SALSA_TYPE_ID]);
            classesSubset -= 1 << (discoveryVotes.length + bestGuess.timeSlot[BACHATA_TYPE_ID]);
            classesSubset -= 1 << bestGuess.timeSlot[DISCOVERY_TYPE_ID];
        }
    }

    private static class Optimizer {
        Map<Integer, BestGuess> memo = new HashMap<>();
        int[] salsaVotes, bachataVotes, discoveryVotes;
        List<Integer>[] sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery,
            sameProfSalsaToSalsa, sameProfBachataToBachata;
        List<Integer>[] salsaProfsOnDuty, bachataProfsOnDuty, discoveryProfsOnDuty;
        ClassLevel[] salsaClassLevels, bachataClassLevels;

        final BestGuess IMPOSSIBLE = new BestGuess(INFINITY);

        final int salsaStylingId;
        final float target;

        Optimizer(int[] salsaVotes, int[] bachataVotes, int[] discoveryVotes, List<Integer>[] sameProfSalsaToBachata,
                  List<Integer>[] sameProfSalsaToDiscovery, List<Integer>[] sameProfBachataToDiscovery,
                  List<Integer>[] sameProfSalsaToSalsa, List<Integer>[] sameProfBachataToBachata,
                  List<Integer>[] salsaProfsOnDuty, List<Integer>[] bachataProfsOnDuty, List<Integer>[] discoveryProfsOnDuty,
                  ClassLevel[] salsaClassLevels, ClassLevel[] bachataClassLevels, int salsaStylingId) {
            this.salsaVotes = salsaVotes;
            this.bachataVotes = bachataVotes;
            this.discoveryVotes = discoveryVotes;
            this.sameProfSalsaToBachata = sameProfSalsaToBachata;
            this.sameProfSalsaToDiscovery = sameProfSalsaToDiscovery;
            this.sameProfBachataToDiscovery = sameProfBachataToDiscovery;
            this.sameProfSalsaToSalsa = sameProfSalsaToSalsa;
            this.sameProfBachataToBachata = sameProfBachataToBachata;
            this.salsaProfsOnDuty = salsaProfsOnDuty;
            this.bachataProfsOnDuty = bachataProfsOnDuty;
            this.discoveryProfsOnDuty = discoveryProfsOnDuty;
            this.salsaClassLevels = salsaClassLevels;
            this.bachataClassLevels = bachataClassLevels;
            this.salsaStylingId = salsaStylingId;
            this.target = (float) (Arrays.stream(salsaVotes).sum() + Arrays.stream(bachataVotes).sum() + Arrays.stream(discoveryVotes).sum())
                        / (salsaVotes.length + bachataVotes.length + discoveryVotes.length);
            System.out.println("--- Average " + target);
        }

        float solve(int timeSlotId, int classesSubset, boolean isExtraSalsaInPreviousSlot, boolean isExtraBachataInPreviousSlot) {
            if (timeSlotId >= TIME_SLOTS) { // Base case
                boolean allClassesOnSchedule = classesSubset == 0;
                return allClassesOnSchedule ? 0.0f : INFINITY;
            }
            BestGuess ans = memo.get(classesSubset);
            if (ans != null) {
                return ans.score;
            }
            ans = guess(timeSlotId, 0, classesSubset, new int[NUM_DANCE_TYPES], isExtraSalsaInPreviousSlot, isExtraBachataInPreviousSlot);
            memo.put(classesSubset, ans);
            return ans.score;
        }

        BestGuess guess(int timeSlotId, int danceTypeId, int classesSubset, int[] timeSlot, boolean isExtraSalsaInPreviousSlot,
                        boolean isExtraBachataInPreviousSlot) {
            int offset = 0, numClasses;
            List<Integer>[] profsOnDuty;
            BestGuess bestGuess = IMPOSSIBLE;
            boolean isExtraSalsaPicked = isExtraSalsaInPreviousSlot, isExtraBachataPicked = isExtraBachataInPreviousSlot;
            switch (danceTypeId) {
                case EXTRA_SALSA_TYPE_ID:
                    timeSlot[danceTypeId] = NO_CLASS;
                    bestGuess = guess(timeSlotId, danceTypeId + 1, classesSubset, timeSlot, isExtraSalsaPicked, isExtraBachataPicked);
                    bestGuess.timeSlot[danceTypeId] = NO_CLASS;
                   {
                       boolean isStylingPicked = timeSlot[SALSA_TYPE_ID] == salsaStylingId;
                        if (isExtraSalsaInPreviousSlot || isStylingPicked) {
                            return bestGuess;
                        }
                   }
                    isExtraSalsaPicked = true;
                case SALSA_TYPE_ID:
                    offset = bachataVotes.length + discoveryVotes.length;
                    numClasses = salsaVotes.length;
                    profsOnDuty = salsaProfsOnDuty;
                    break;
                case EXTRA_BACHATA_TYPE_ID:
                    timeSlot[danceTypeId] = -1;
                    bestGuess = guess(timeSlotId, danceTypeId + 1, classesSubset, timeSlot, isExtraSalsaPicked, isExtraBachataPicked);
                    bestGuess.timeSlot[danceTypeId] = -1;
                    boolean isFourthDanceAlreadyPicked = timeSlot[EXTRA_SALSA_TYPE_ID] >= 0;
                    {
                        boolean isStylingPicked = timeSlot[SALSA_TYPE_ID] == salsaStylingId;
                        if (isExtraBachataInPreviousSlot || isFourthDanceAlreadyPicked || isStylingPicked) {
                            return bestGuess;
                        }
                    }
                    isExtraBachataPicked = true;
                case BACHATA_TYPE_ID:
                    offset = discoveryVotes.length;
                    numClasses = bachataVotes.length;
                    profsOnDuty = bachataProfsOnDuty;
                    break;
                case DISCOVERY_TYPE_ID:
                    numClasses = discoveryVotes.length;
                    profsOnDuty = discoveryProfsOnDuty;
                    break;
                case END_OF_TIME_SLOT:
                    float lookup = solve(timeSlotId + 1, classesSubset, isExtraSalsaPicked, isExtraBachataPicked);
                    return lookup > SCORE_LIMIT ? IMPOSSIBLE : new BestGuess(computeScore(timeSlot, timeSlotId) + lookup);
                default:
                    throw null;
            }
            for (int currClass = 0; currClass < numClasses; ++currClass) {
                boolean isStylingInExtraSalsa = danceTypeId == EXTRA_SALSA_TYPE_ID && currClass == salsaStylingId;
                if (isProfAlreadyInTimeSlot(currClass, danceTypeId, timeSlot) || isProfOnDuty(currClass, timeSlotId, profsOnDuty)
                    || isClassLevelAlreadyInTimeSlot(currClass, danceTypeId, timeSlot) || isStylingInExtraSalsa) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked =  (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[danceTypeId] = currClass;
                    BestGuess someGuess = guess(timeSlotId, danceTypeId + 1, classesSubset - classMask, timeSlot,
                            isExtraSalsaPicked, isExtraBachataPicked);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[danceTypeId] = currClass;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        boolean isProfAlreadyInTimeSlot(int currClass, int danceType, int[] timeSlot) {
            switch (danceType) {
                case SALSA_TYPE_ID:
                    return false;
                case EXTRA_SALSA_TYPE_ID:
                    return sameProfSalsaToSalsa[timeSlot[SALSA_TYPE_ID]].stream().anyMatch(
                            otherSalsa -> otherSalsa == currClass);
                case BACHATA_TYPE_ID:
                    return sameProfSalsaToBachata[timeSlot[SALSA_TYPE_ID]].stream().anyMatch(
                                otherBachata -> otherBachata == currClass)
                            || (timeSlot[EXTRA_SALSA_TYPE_ID] >= 0 && sameProfSalsaToBachata[timeSlot[EXTRA_SALSA_TYPE_ID]]
                                .stream().anyMatch(otherBachata -> otherBachata == currClass));
                case EXTRA_BACHATA_TYPE_ID:
                    return sameProfSalsaToBachata[timeSlot[SALSA_TYPE_ID]].stream().anyMatch(
                                otherBachata -> otherBachata == currClass)
                            || sameProfBachataToBachata[timeSlot[BACHATA_TYPE_ID]].stream().anyMatch(
                                otherBachata -> otherBachata == currClass);
                case DISCOVERY_TYPE_ID:
                    return sameProfSalsaToDiscovery[timeSlot[SALSA_TYPE_ID]].stream().anyMatch(
                                otherDiscovery -> otherDiscovery == currClass)
                            || (timeSlot[EXTRA_SALSA_TYPE_ID] >= 0 && sameProfSalsaToDiscovery[timeSlot[EXTRA_SALSA_TYPE_ID]]
                                .stream().anyMatch(otherDiscovery -> otherDiscovery == currClass))
                            || sameProfBachataToDiscovery[timeSlot[BACHATA_TYPE_ID]].stream().anyMatch(
                                otherDiscovery -> otherDiscovery == currClass)
                            || (timeSlot[EXTRA_BACHATA_TYPE_ID] >= 0 && sameProfBachataToDiscovery[timeSlot[EXTRA_BACHATA_TYPE_ID]]
                                .stream().anyMatch(otherDiscovery -> otherDiscovery == currClass));
                default:
                    throw null;
            }
        }

        boolean isClassLevelAlreadyInTimeSlot(int currClass, int danceType, int[] timeSlot) {
            switch (danceType) {
                case SALSA_TYPE_ID:
                    return false;
                case EXTRA_SALSA_TYPE_ID:
                    return salsaClassLevels[timeSlot[SALSA_TYPE_ID]] == salsaClassLevels[currClass];
                case BACHATA_TYPE_ID:
                    return timeSlot[EXTRA_SALSA_TYPE_ID] < 0 // if we have 3 classes then alternate the difficulty, otherwise ignore
                            && salsaClassLevels[timeSlot[SALSA_TYPE_ID]] == bachataClassLevels[currClass];
                case EXTRA_BACHATA_TYPE_ID:
                    return bachataClassLevels[timeSlot[BACHATA_TYPE_ID]] == bachataClassLevels[currClass];
                case DISCOVERY_TYPE_ID:
                    return false; // discoveries are all beginners
                default:
                    throw null;
            }
        }

        boolean isProfOnDuty(int currClass, int timeSlotId, List<Integer>[] profsOnDuty) {
            return profsOnDuty[currClass].stream().anyMatch(dutySlot -> dutySlot == timeSlotId);
        }

        private float computeAverage(int[] timeSlot) {
            int sum = salsaVotes[timeSlot[SALSA_TYPE_ID]] + bachataVotes[timeSlot[BACHATA_TYPE_ID]]
                    + discoveryVotes[timeSlot[DISCOVERY_TYPE_ID]];
            float count = 4.0f;
            if (timeSlot[EXTRA_SALSA_TYPE_ID] >= 0) {
                sum += salsaVotes[timeSlot[EXTRA_SALSA_TYPE_ID]];
            } else if (timeSlot[EXTRA_BACHATA_TYPE_ID] >= 0) {
                sum += bachataVotes[timeSlot[EXTRA_BACHATA_TYPE_ID]];
            } else {
                count = 3.0f;
            }
            return sum / count;
        }
        float computeScore(int[] timeSlot, int timeSlotId) {
            float meanAbsoluteError = Math.abs(computeAverage(timeSlot) - target);
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
            return meanAbsoluteError;
        }

        float computeSimilarity(int[] timeSlot) {
            return computeAverage(timeSlot) / target * 100.0f;
        }
    }

    enum ClassLevel {
        INTERMEDIATE,
        ADVANCED
    }

    private static class BestGuess {
        float score;
        int[] timeSlot;

        BestGuess(float score) {
            this.score = score;
            timeSlot = new int[NUM_DANCE_TYPES];
        }
    }
}
