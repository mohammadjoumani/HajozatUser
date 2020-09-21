package com.example.hajozatuser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.security.AuthenticationManager;
import com.esri.arcgisruntime.security.DefaultAuthenticationChallengeHandler;
import com.esri.arcgisruntime.security.OAuthConfiguration;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.tasks.networkanalysis.Route;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteParameters;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteResult;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteTask;
import com.esri.arcgisruntime.tasks.networkanalysis.Stop;
import com.example.hajozatuser.Common.Common;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class maps extends AppCompatActivity {
    private MapView mMapView;

    private GraphicsOverlay mGraphicsOverlay;
    private Point mStart;
    private Point mEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_maps );

        mMapView = findViewById( R.id.mapView );

        //Create map and add it to MapView
        setupMap();
        //Use this method to create Layer to add points and routes and etc
        createGraphicsOverlay();
        //Use this method for create routes (route need authentication)
        setupOAuthManager();

        //Wkid = 3857
        SpatialReference ESPG_3857 = SpatialReference.create( 3857 );

        mStart = new Point( 1494547.5241428744, 6889656.619328567, 0.0, ESPG_3857 );
//        mEnd = new Point(getIntent().getExtras().getDouble("Lat") , getIntent().getExtras().getDouble("Lng") , 0.0 , ESPG_3857);
        mEnd = new Point( Common.o.getLat(), Common.o.getLng(), 0.0, ESPG_3857 );

        //Focus on mStart Point
        mMapView.setViewpointCenterAsync( mStart, 5000 );
        //Add mStart and mEnd points to map
        setStartMarker( mStart );
        setEndMarker( mEnd );


//        mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mMapView) {
//            @Override public boolean onSingleTapConfirmed(MotionEvent e) {
//                android.graphics.Point screenPoint = new android.graphics.Point(
//                        Math.round(e.getX()),
//                        Math.round(e.getY()));
//                Point mapPoint = mMapView.screenToLocation(screenPoint);
//                mapClicked(mapPoint);
//                return super.onSingleTapConfirmed(e);
//            }
//        });

    }

    private void mapClicked(Point location) {
        if (mStart == null) {
            // Start is not set, set it to a tapped location
            setStartMarker( location );
        } else if (mEnd == null) {
            // End is not set, set it to the tapped location then find the route
            setEndMarker( location );
        } else {
            // Both locations are set; re-set the start to the tapped location
            setStartMarker( location );
        }
    }

    private void setupMap() {

        if (mMapView != null) {
            Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
            //Details of mStart point
            double latitude = 1494547.5241428744;
            double longitude = 6889656.619328567;

            int levelOfDetail = 13;
            ArcGISMap map = new ArcGISMap( basemapType, latitude, longitude, levelOfDetail );
            mMapView.setMap( map );
        }
    }

    private void createGraphicsOverlay() {
        mGraphicsOverlay = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add( mGraphicsOverlay );
    }

    private void setMapMarker(Point location, SimpleMarkerSymbol.Style style, int markerColor, int outlineColor, int status) {

        float markerSize = 14.0f;
        float markerOutlineThickness = 2.0f;
        SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol( style, markerColor, markerSize );
        pointSymbol.setOutline( new SimpleLineSymbol( SimpleLineSymbol.Style.SOLID, outlineColor, markerOutlineThickness ) );

        Bitmap bitmap;
        if (status == 0) //mStart point
            bitmap = Common.getBitmapFromVectorDrawable( getApplicationContext(), R.drawable.ic_baseline_location_on_24 );
        else  //mEnd point
            bitmap = Common.getBitmapFromVectorDrawable( getApplicationContext(), R.drawable.ic_baseline_location_city_24 );

        BitmapDrawable pinStarBlueDrawable = new BitmapDrawable( bitmap );
        final PictureMarkerSymbol pinStarBlueSymbol = new PictureMarkerSymbol( pinStarBlueDrawable );
        Graphic pointGraphic = new Graphic( location, pinStarBlueSymbol );

        pinStarBlueSymbol.setHeight( 40 );
        pinStarBlueSymbol.setWidth( 40 );
        pinStarBlueSymbol.setOffsetY( 11 );
        pinStarBlueSymbol.loadAsync();
        mGraphicsOverlay.getGraphics().add( pointGraphic );


//        float markerSize = 8.0f;
//        float markerOutlineThickness = 2.0f;
//        SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(style, markerColor, markerSize);
//        pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, outlineColor, markerOutlineThickness));
//        Graphic pointGraphic = new Graphic(location, pointSymbol);
//        mGraphicsOverlay.getGraphics().add(pointGraphic);


    }

    private void setStartMarker(Point location) {

        //Remove all points and routes
        mGraphicsOverlay.getGraphics().clear();
        setMapMarker( location, SimpleMarkerSymbol.Style.DIAMOND, Color.rgb( 226, 119, 40 ), Color.BLUE, 0 );
        mStart = location;
//        mEnd = null;

    }

    private void setEndMarker(Point location) {
        setMapMarker( location, SimpleMarkerSymbol.Style.DIAMOND, Color.rgb( 40, 119, 226 ), Color.RED, 1 );
        mEnd = location;
        findRoute();
    }

    private void setupOAuthManager() {
        String clientId = getResources().getString( R.string.client_id );
        String redirectUrl = getResources().getString( R.string.redirect_url );
        try {
            OAuthConfiguration oAuthConfiguration = new OAuthConfiguration( "https://www.arcgis.com", clientId, redirectUrl );
            DefaultAuthenticationChallengeHandler authenticationChallengeHandler = new DefaultAuthenticationChallengeHandler( this );
            AuthenticationManager.setAuthenticationChallengeHandler( authenticationChallengeHandler );
            AuthenticationManager.addOAuthConfiguration( oAuthConfiguration );
        } catch (MalformedURLException e) {
            showError( e.getMessage() );
        }
    }

    private void showError(String message) {
        Log.d( "FindRoute", message );
        Toast.makeText( getApplicationContext(), message, Toast.LENGTH_LONG ).show();
    }

    private void findRoute() {

        String routeServiceURI = getResources().getString( R.string.routing_url );
        final RouteTask solveRouteTask = new RouteTask( getApplicationContext(), routeServiceURI );
        solveRouteTask.loadAsync();
        solveRouteTask.addDoneLoadingListener( () -> {

            if (solveRouteTask.getLoadStatus() == LoadStatus.LOADED) {
                final ListenableFuture<RouteParameters> routeParamsFuture = solveRouteTask.createDefaultParametersAsync();
                routeParamsFuture.addDoneListener( () -> {
                    try {
                        RouteParameters routeParameters = routeParamsFuture.get();
                        List<Stop> stops = new ArrayList<>();
                        stops.add( new Stop( mStart ) );
                        stops.add( new Stop( mEnd ) );
                        routeParameters.setStops( stops );

                        final ListenableFuture<RouteResult> routeResultFuture = solveRouteTask.solveRouteAsync( routeParameters );
                        routeResultFuture.addDoneListener( () -> {
                            try {
                                RouteResult routeResult = routeResultFuture.get();
                                Route firstRoute = routeResult.getRoutes().get( 0 );

                                Polyline routePolyline = firstRoute.getRouteGeometry();
                                SimpleLineSymbol routeSymbol = new SimpleLineSymbol( SimpleLineSymbol.Style.SOLID, Color.BLUE, 3.0f );
                                Graphic routeGraphic = new Graphic( routePolyline, routeSymbol );
                                mGraphicsOverlay.getGraphics().add( routeGraphic );

                            } catch (InterruptedException | ExecutionException e) {
                                showError( "Solve RouteTask failed " + e.getMessage() );
                            }
                        } );

                    } catch (InterruptedException | ExecutionException e) {
                        showError( "Cannot create RouteTask parameters " + e.getMessage() );
                    }
                } );
            } else {
                showError( "Unable to load RouteTask " + solveRouteTask.getLoadStatus().toString() );
            }

        } );
    }

    @Override
    protected void onPause() {
        if (mMapView != null) {
            mMapView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mMapView != null) {
            mMapView.dispose();
        }
        super.onDestroy();
    }
}