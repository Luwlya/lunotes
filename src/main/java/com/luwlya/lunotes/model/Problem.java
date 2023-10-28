package com.luwlya.lunotes.model;

/**
 * Represents error response
 *
 * @param status  http status of the response (for example: 500)
 * @param title   title of the response (for example: BAD_REQUEST)
 * @param details error details
 */
public record Problem(int status, String title, String details) {
}
