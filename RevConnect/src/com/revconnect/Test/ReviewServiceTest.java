package com.revconnect.Test;
import com.revconnect.model.Review;
import com.revconnect.service.ReviewService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ReviewServiceTest
{
     ReviewService reviewService = new ReviewService();

        @Test
        void testAddReview() {
            Review r = new Review(1, 2, 4, "Good profile");

            boolean result = reviewService.addReview(r);

            assertTrue(result);
        }

        @Test
        void testGetAverageRating() {
            double avg = reviewService.getAvgRating(2);

            assertTrue(avg >= 0);
        }

}
