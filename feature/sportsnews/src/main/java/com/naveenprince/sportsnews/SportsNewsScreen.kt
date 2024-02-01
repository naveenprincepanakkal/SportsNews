package com.naveenprince.sportsnews

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.naveenprince.common.utils.Utils
import com.naveenprince.common.utils.Utils.Companion.timeFormatConversion
import com.naveenprince.domain.model.SportsNews


/**
 * Created by Naveen.
 */
@Composable
fun SportsNewsScreen(
    viewModel: SportsNewsViewModel = hiltViewModel()
) {
    val latestNewsState by viewModel.newsState.collectAsState(initial = SportsNewsState())
    SportsNewsScreen(latestNewsState)
}

@Composable
fun SportsNewsScreen(
    latestNewsState: SportsNewsState
) {
    LatestNewsView(latestNewsState.sportsResults ?: emptyList())

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (latestNewsState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.testTag("LOADING"))
        } else {
            if (latestNewsState.error != null) {
                Text(
                    text = latestNewsState.error.toString(),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.testTag("ERROR")
                )
            } else if (latestNewsState.sportsResults.isNullOrEmpty()) {
                Text(text = stringResource(R.string.no_news_available))
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
            text = date?.let { stringResource(R.string.result_for, date) }
                ?: stringResource(R.string.date_unknown),
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
                ) ?: stringResource(R.string.time_unknown),
                style = MaterialTheme.typography.labelLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = sportsResult.news ?: stringResource(R.string.news_unknown),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

