package com.gitje.flickrbrowser

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus {
    OK, IDLE, NOT_INITIALISED, FAILED_OR_EMPTY, PREMISSIONS_ERROR, ERROR
}

class GetRawData(private val listener: OnDownloadComplete) : AsyncTask<String, Void, String>() {
    private val TAG = "GetRawData"
    private var downloadStatus = DownloadStatus.IDLE

    interface OnDownloadComplete {
        fun onDownloadComplete(data: String, status: DownloadStatus)
    }

    override fun doInBackground(vararg params: String?): String {
        if (params[0] == null) {
            downloadStatus = DownloadStatus.NOT_INITIALISED
            return "No URL specified"
        }

        try {
            downloadStatus = DownloadStatus.OK
            return URL(params[0]).readText()
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is MalformedURLException -> {
                    downloadStatus = DownloadStatus.NOT_INITIALISED
                    "DoInBackground: Invalid URL: ${e.message}"
                }
                is IOException -> {
                    downloadStatus = DownloadStatus.FAILED_OR_EMPTY
                    "DoInBackground: IOException reading data: ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus = DownloadStatus.PREMISSIONS_ERROR
                    "DoInBackground: Security Error: Needs permission? : ${e.message}"
                }
                else -> {
                    downloadStatus = DownloadStatus.ERROR
                    "DoInBackground: Unknown Error : ${e.message}"
                }
            }
            Log.e(TAG, errorMessage)
            return errorMessage
        }
    }

    override fun onPostExecute(result: String) {
        Log.d(TAG, "onPostExecute called")
        listener.onDownloadComplete(result, downloadStatus)
    }
}