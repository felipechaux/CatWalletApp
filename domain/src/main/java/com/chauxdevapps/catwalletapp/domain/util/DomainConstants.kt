package com.chauxdevapps.catwalletapp.domain.util

object DomainConstants {
    // Favorite Limits
    const val FREE_FAVORITE_LIMIT = 3
    
    // Error Messages
    const val ERROR_LIMIT_REACHED = "LIMIT_REACHED"
    const val ERROR_INVALID_CARD_NUMBER = "Invalid Card Number"
    const val ERROR_TOKENIZATION_FAILED = "Error tokenizing payment method. Please try again."
    
    // Tokenization
    const val TOKEN_PREFIX = "tok_"
    const val TOKEN_LENGTH = 12
    
    // Network
    const val MOCK_DELAY_MS = 1500L
    const val MIN_CARD_NUMBER_LENGTH = 13
}
