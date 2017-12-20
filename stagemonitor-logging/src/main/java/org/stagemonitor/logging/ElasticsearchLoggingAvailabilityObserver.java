package org.stagemonitor.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stagemonitor.core.elasticsearch.AbstractElasticsearchFirstAvailabilityObserver;
import org.stagemonitor.core.elasticsearch.ElasticsearchClient;

public class ElasticsearchLoggingAvailabilityObserver  extends AbstractElasticsearchFirstAvailabilityObserver{
	private final Logger logger = LoggerFactory.getLogger(ElasticsearchLoggingAvailabilityObserver.class);

	@Override
	protected void onElasticsearchFirstAvailable(ElasticsearchClient elasticsearchClient) {
		logger.info("sending grafana ElasticsearchLogging...");
		grafanaClient.sendGrafanaDashboardAsync("grafana/ElasticsearchLogging.json");
		elasticsearchClient.sendClassPathRessourceBulkAsync("kibana/Logging.bulk", true);
		logger.info("sent grafana ElasticsearchLogging...");
	}

	@Override
	public int getPriority() {
		return 0;
	}
}
