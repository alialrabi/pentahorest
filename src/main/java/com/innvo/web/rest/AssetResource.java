package com.innvo.web.rest;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.codahale.metrics.annotation.Timed;
import com.innvo.domain.Asset;

import com.innvo.repository.AssetRepository;
import com.innvo.web.rest.util.HeaderUtil;
import com.innvo.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

/**
 * REST controller for managing Asset.
 */
@RestController
@RequestMapping("/api")
public class AssetResource {

    private final Logger log = LoggerFactory.getLogger(AssetResource.class);

    private static final String ENTITY_NAME = "asset";
        
    private final AssetRepository assetRepository;

    public AssetResource(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    /**
     * POST  /assets : Create a new asset.
     *
     * @param asset the asset to create
     * @return the ResponseEntity with status 201 (Created) and with body the new asset, or with status 400 (Bad Request) if the asset has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/assets")
    @Timed
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) throws URISyntaxException {
        log.debug("REST request to save Asset : {}", asset);
        if (asset.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new asset cannot already have an ID")).body(null);
        }
        Asset result = assetRepository.save(asset);
        return ResponseEntity.created(new URI("/api/assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /assets : Updates an existing asset.
     *
     * @param asset the asset to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated asset,
     * or with status 400 (Bad Request) if the asset is not valid,
     * or with status 500 (Internal Server Error) if the asset couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/assets")
    @Timed
    public ResponseEntity<Asset> updateAsset(@RequestBody Asset asset) throws URISyntaxException {
        log.debug("REST request to update Asset : {}", asset);
        if (asset.getId() == null) {
            return createAsset(asset);
        }
        Asset result = assetRepository.save(asset);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, asset.getId().toString()))
            .body(result);
    }

    /**
     * GET  /assets : get all the assets.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assets in body
     */
    @GetMapping("/assets")
    @Timed
    public ResponseEntity<List<Asset>> getAllAssets(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Assets");
        Page<Asset> page = assetRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/assets");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /assets/:id : get the "id" asset.
     *
     * @param id the id of the asset to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the asset, or with status 404 (Not Found)
     */
    @GetMapping("/assets/{id}")
    @Timed
    public ResponseEntity<Asset> getAsset(@PathVariable Long id) {
        log.debug("REST request to get Asset : {}", id);
        Asset asset = assetRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(asset));
    }

    /**
     * DELETE  /assets/:id : delete the "id" asset.
     *
     * @param id the id of the asset to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/assets/{id}")
    @Timed
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        log.debug("REST request to delete Asset : {}", id);
        assetRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * 
     * @param request
     * @return
     */
    @PostMapping("/saveAsset")
    @Timed
    public Asset saveAsset(HttpServletRequest request) {
        log.debug("REST request to save Asset");
        Double doubleId=Double.parseDouble(request.getParameter("id"));
        long id=doubleId.longValue();
        Asset asset = new Asset();
        asset.setId(id);
        asset.setName(request.getParameter("name"));
        asset.setNameshort(request.getParameter("nameshort"));
        asset.setDescription(request.getParameter("description"));
        asset.setDomain(request.getParameter("domain"));
        asset.setLastmodifedby(request.getParameter("lsetLastmodifedby"));
        asset.setStatus(request.getParameter("status"));
        Asset result=assetRepository.save(asset);
        
        return result;
      
    }
    
    
    /**
     * 
     * @throws KettleException
     * @throws IOException 
     */
    @GetMapping("/runKettle")
    @Timed
    public void runKettle() throws IOException {
    	  log.debug("REST request to run kettle file");
          
          String accessKey= "AKIAIC2M74X5UIUMUZFA";
          String secretKey= "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";
          
          AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
  		System.out.println("11111111111111111111111111111111111111111111111111111111");
  	     String bucketName = "innvo-pentaho/transformations";  
           String key = "transformation1.ktr"; 
        System.out.println("22222222222222222222*******22222222222222222222222222222222222");

   		 File tempFile = File.createTempFile("transformation1", ".ktr"); 
   		 System.out.println("333333333333333333333333333333333333333333333333333333333333");
   		 AmazonS3 AMAZON_S3 = new AmazonS3Client(credentials);
           System.out.println("4444444444444444444444444///4444444444444444444444444444444444444444444444444");
           AMAZON_S3.getObject(new GetObjectRequest(bucketName, key), tempFile); 
          
           System.out.println("555555555555555555555555555555555555555555555555555555555");
           System.out.println(tempFile.getAbsolutePath());
           System.out.println("6666666666666666666777777---7777777766666666666666666666666666666666");
           try{
          KettleEnvironment.init();
        //  URL ktrUrl = PipelineResource.class.getClassLoader().getResource(tempFile.getAbsolutePath());
          TransMeta transMeta = new TransMeta(tempFile.getAbsolutePath());
          Trans trans = new Trans(transMeta);
          trans.execute(new String[]{});
          trans.waitUntilFinished();
           }catch (KettleException e) {
        	   e.getSuperMessage();
        	   System.out.println("1111111111111111111111111111111111111");
			e.printStackTrace();
		}
    }
}
