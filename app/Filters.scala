import javax.inject.Inject

import play.api.http.DefaultHttpFilters
import play.mvc.EssentialFilter
import play.filters.csrf.CSRFFilter
import play.filters.headers.SecurityHeadersFilter
import play.filters.hosts.AllowedHostsFilter

/**
  *  This class configures filters that run on every request. This
  *  class is queried by Play to get a list of filters.
  * 
  *  Play will automatically use filters from any class called
  *  `Filters` that is placed the root package. You can load filters
  *  from a different class by adding a `play.http.filters` setting to
  *  the `application.conf` configuration file.
  * 
  *  @param env Basic environment settings for the current application.
  *  @param exampleFilter A demonstration filter that adds a header to
  *  each response.
  */
class Filters @Inject() (
  csrfFilter: CSRFFilter,
  allowedHostsFilter: AllowedHostsFilter,
  securityHeadersFilter: SecurityHeadersFilter
) extends DefaultHttpFilters(
  csrfFilter,
  allowedHostsFilter,
  securityHeadersFilter
)
