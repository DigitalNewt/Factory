package model.service

import com.amazonservices.mws.orders._2013_09_01.{MarketplaceWebServiceOrdersAsyncClient, MWSEndpoint, MarketplaceWebServiceOrdersClient}
import java.net.URI

/**
  * Created by Brent Baker on 11/9/15.
  */
class AwsOrdersConfig {

  private val awsAccessKey:String = "AKIAIW2L6PJKIGHJASVQ"
  private val awsSecretKey:String = "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"
  private val awsAppName:String = "HBB Order Listener"
  private val awsAppVersion:String = "2015-5-23"
  private val awsServiceUrl:URI = MWSEndpoint.NA_PROD
  protected var awsClient:MarketplaceWebServiceOrdersAsyncClient //= Option(getAsyncClient)

  def getClient:MarketplaceWebServiceOrdersAsyncClient = {
    getAsyncClient
  }

  def getAsyncClient:MarketplaceWebServiceOrdersAsyncClient = {
    var config = new AwsOrdersConfig()
    config.

  }



}
