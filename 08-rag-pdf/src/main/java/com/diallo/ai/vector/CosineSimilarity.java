package com.diallo.ai.vector;

public final class CosineSimilarity {

    private CosineSimilarity() {
    }

    public static double calculate(float[] vector1, float[] vector2) {

        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException(
                    "Les deux vecteurs doivent avoir la même dimension.");
        }

        double dotProduct = 0.0;
        double normVector1 = 0.0;
        double normVector2 = 0.0;

        for (int i = 0; i < vector1.length; i++) {

            dotProduct += vector1[i] * vector2[i];

            normVector1 += Math.pow(vector1[i], 2);

            normVector2 += Math.pow(vector2[i], 2);
        }

        double denominator = Math.sqrt(normVector1) * Math.sqrt(normVector2);

        if (denominator == 0) {
            return 0;
        }

        return dotProduct / denominator;
    }

}