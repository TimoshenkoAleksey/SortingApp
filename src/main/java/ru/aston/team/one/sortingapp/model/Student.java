package ru.aston.team.one.sortingapp.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Student.Builder.class)
public record Student(int groupNumber, double averageNumber, int recordBookNumber) implements Comparable<Student> {

    private static final double MIN_AVERAGE = 0.0;
    private static final double MAX_AVERAGE = 5.0;

    public Student {
        if (groupNumber <= 0) {
            throw new IllegalArgumentException("Номер группы должен быть больше 0: " + groupNumber);
        }

        if (averageNumber < MIN_AVERAGE || averageNumber > MAX_AVERAGE || Double.isNaN(averageNumber)) {
            throw new IllegalArgumentException("Средний балл должен быть от " + MIN_AVERAGE + " до "
                    + MAX_AVERAGE + ": " + averageNumber);
        }

        if (recordBookNumber <= 0) {
            throw new IllegalArgumentException("Номер зачетной книжки должен быть больше 0: " + recordBookNumber);
        }
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(recordBookNumber, other.recordBookNumber);
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
    public static class Builder {

        private int groupNumber;
        private double averageNumber;
        private int recordBookNumber;

        public Builder groupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder averageNumber(double averageNumber) {
            this.averageNumber = averageNumber;
            return this;
        }

        public Builder recordBookNumber(int recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(groupNumber,averageNumber,recordBookNumber);
        }
    }
}
