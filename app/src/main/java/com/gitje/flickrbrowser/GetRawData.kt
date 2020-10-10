package com.gitje.flickrbrowser

enum class DownloadStatus {
    OK, IDLE, NOT_INITIALISED, FAILED_OR_EMPTY, PREMISSIONS_ERROR, ERROR
}

class GetRawData {
    private val TAG = "GetRawData"
    private val downloadStatus = DownloadStatus.IDLE
}