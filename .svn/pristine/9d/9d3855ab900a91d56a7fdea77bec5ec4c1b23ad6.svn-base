package com.hgicreate.rno.xdr.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hgicreate.rno.xdr.backend.domain.DataSearch;

import com.hgicreate.rno.xdr.backend.repository.DataSearchRepository;
import com.hgicreate.rno.xdr.backend.repository.search.DataSearchSearchRepository;
import com.hgicreate.rno.xdr.backend.service.DataSearchService;
import com.hgicreate.rno.xdr.backend.web.rest.cond.DataSearchCond;
import com.hgicreate.rno.xdr.backend.web.rest.util.HeaderUtil;
import com.hgicreate.rno.xdr.backend.web.rest.util.PaginationUtil;
import com.hgicreate.rno.xdr.backend.service.dto.DataSearchDTO;
import com.hgicreate.rno.xdr.backend.service.mapper.DataSearchMapper;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing DataSearch.
 */
@RestController
@RequestMapping("/api")
public class DataSearchResource {

    private final Logger log = LoggerFactory.getLogger(DataSearchResource.class);

    private static final String ENTITY_NAME = "dataSearch";

    private final DataSearchRepository dataSearchRepository;

    private final DataSearchMapper dataSearchMapper;

    private final DataSearchSearchRepository dataSearchSearchRepository;

    private final DataSearchService dataSearchService;

    public DataSearchResource(DataSearchRepository dataSearchRepository, DataSearchMapper dataSearchMapper, DataSearchSearchRepository dataSearchSearchRepository, DataSearchService dataSearchService) {
        this.dataSearchRepository = dataSearchRepository;
        this.dataSearchMapper = dataSearchMapper;
        this.dataSearchSearchRepository = dataSearchSearchRepository;
        this.dataSearchService = dataSearchService;
    }

    /**
     * POST  /data-searches : Create a new dataSearch.
     *
     * @param dataSearchDTO the dataSearchDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dataSearchDTO, or with status 400 (Bad Request) if the dataSearch has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/data-searches")
    @Timed
    public ResponseEntity<DataSearchDTO> createDataSearch(@RequestBody DataSearchDTO dataSearchDTO) throws URISyntaxException {
        log.debug("REST request to save DataSearch : {}", dataSearchDTO);
        if (dataSearchDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new dataSearch cannot already have an ID")).body(null);
        }
        DataSearch dataSearch = dataSearchMapper.toEntity(dataSearchDTO);
        dataSearch = dataSearchRepository.save(dataSearch);
        DataSearchDTO result = dataSearchMapper.toDto(dataSearch);
        dataSearchSearchRepository.save(dataSearch);
        return ResponseEntity.created(new URI("/api/data-searches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /data-searches : Updates an existing dataSearch.
     *
     * @param dataSearchDTO the dataSearchDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dataSearchDTO,
     * or with status 400 (Bad Request) if the dataSearchDTO is not valid,
     * or with status 500 (Internal Server Error) if the dataSearchDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/data-searches")
    @Timed
    public ResponseEntity<DataSearchDTO> updateDataSearch(@RequestBody DataSearchDTO dataSearchDTO) throws URISyntaxException {
        log.debug("REST request to update DataSearch : {}", dataSearchDTO);
        if (dataSearchDTO.getId() == null) {
            return createDataSearch(dataSearchDTO);
        }
        DataSearch dataSearch = dataSearchMapper.toEntity(dataSearchDTO);
        dataSearch = dataSearchRepository.save(dataSearch);
        DataSearchDTO result = dataSearchMapper.toDto(dataSearch);
        dataSearchSearchRepository.save(dataSearch);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dataSearchDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /data-searches : get all the dataSearches.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dataSearches in body
     */
    @GetMapping("/data-searches")
    @Timed
    public ResponseEntity<List<DataSearchDTO>> getAllDataSearches(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DataSearches");
        Page<DataSearch> page = dataSearchRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/data-searches");
        return new ResponseEntity<>(dataSearchMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /data-searches/:id : get the "id" dataSearch.
     *
     * @param id the id of the dataSearchDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dataSearchDTO, or with status 404 (Not Found)
     */
    @GetMapping("/data-searches/{id}")
    @Timed
    public ResponseEntity<DataSearchDTO> getDataSearch(@PathVariable Long id) {
        log.debug("REST request to get DataSearch : {}", id);
        DataSearch dataSearch = dataSearchRepository.findOne(id);
        DataSearchDTO dataSearchDTO = dataSearchMapper.toDto(dataSearch);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dataSearchDTO));
    }

    /**
     * DELETE  /data-searches/:id : delete the "id" dataSearch.
     *
     * @param id the id of the dataSearchDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/data-searches/{id}")
    @Timed
    public ResponseEntity<Void> deleteDataSearch(@PathVariable Long id) {
        log.debug("REST request to delete DataSearch : {}", id);
        dataSearchRepository.delete(id);
        dataSearchSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/data-searches?query=:query : search for the dataSearch corresponding
     * to the query.
     *
     * @param cond the query of the dataSearch search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/data-searches")
    @Timed
    public ResponseEntity<List<DataSearchDTO>> searchDataImports(@ApiParam DataSearchCond cond, @ApiParam Pageable pageable) {
        log.debug("REST request to get a page of data-searches : cond = {}, pageable = {}", cond, pageable);
        Page<DataSearchDTO> page = dataSearchService.findByCond(cond, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/_search/data-searches");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/_search/data-download")
    @Timed
    public ResponseEntity<List<DataSearchDTO>> downloadByCond(@ApiParam DataSearchCond cond) {
        log.debug("REST request to get a page of data-search : cond = {}", cond);
        List<DataSearchDTO> list = dataSearchService.downloadByCond(cond);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(list));
    }

}
