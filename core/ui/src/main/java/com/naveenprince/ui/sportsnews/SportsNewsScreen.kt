package com.naveenprince.ui.sportsnews

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.naveenprince.common.model.SportsNews
import com.naveenprince.common.utils.Utils
import com.naveenprince.common.utils.Utils.Companion.timeFormatConversion
import com.naveenprince.sportsnews.SportsNewsState
import com.naveenprince.sportsnews.SportsNewsViewModel


/**
 * Created by Naveen.
 */
@Composable
fun SportsNewsScreen(
    viewModel: SportsNewsViewModel = hiltViewModel()
) {
    var latestNewsState by remember { mutableStateOf(SportsNewsState()) }

    LaunchedEffect(true) {
        viewModel.newsState.collect {
            latestNewsState = it
        }
    }

    LatestNewsView(latestNewsState.sportsResults ?: emptyList())

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (latestNewsState.isLoading) {
            CircularProgressIndicator()
        } else {
            if (latestNewsState.error != null) {
                Text(
                    text = latestNewsState.error.toString(),
                    color = MaterialTheme.colorScheme.error
                )
            } else if (latestNewsState.sportsResults == null) {
                Text(text = "No data")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LatestNewsView(
    sportsResults: List<SportsNews>
) {

    val groupedResults = sportsResults.groupBy {
        it.publicationDate?.let { dateString ->
            dateString.timeFormatConversion(
                inputFormat = Utils.TIME_FORMAT_2,
                outputFormat = Utils.TIME_FORMAT_3
            )
        }
    }

    LazyColumn {
        groupedResults.forEach { (date, items) ->
            stickyHeader {
                NewsHeaderView(date)
            }
            items(items) { item ->
                NewsItemCardView(item)
            }
        }

    }
}

@Composable
fun NewsHeaderView(date: String?) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(12.dp, 6.dp)
    ) {
        Text(
            text = date?.let { "Result for $date" } ?: "Date unknown",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
fun NewsItemCardView(sportsResult: SportsNews) {
    Card(
        modifier = Modifier
            .padding(12.dp, 6.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = sportsResult.publicationDate?.timeFormatConversion(
                    inputFormat = Utils.TIME_FORMAT_2,
                    outputFormat = Utils.TIME_FORMAT_4
                ) ?: "Time unknown",
                style = MaterialTheme.typography.labelLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = sportsResult.news ?: "No news available",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

