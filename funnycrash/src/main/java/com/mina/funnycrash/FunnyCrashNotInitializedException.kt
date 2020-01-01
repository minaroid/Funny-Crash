package com.mina.funnycrash

/**
 * Created by bali on 02/08/17.
 */
/**
 * An Exception indicating that the Crash Reporter has not been correctly initialized.
 */
class FunnyCrashNotInitializedException : FunnyCrashException {

    /**
     * Constructs a CrashReporterNotInitializedException with a message.
     *
     * @param message A String to be returned from getMessage.
     */
    constructor(message: String?) : super(message) {}

    companion object {
        const val serialVersionUID: Long = 1
    }
}