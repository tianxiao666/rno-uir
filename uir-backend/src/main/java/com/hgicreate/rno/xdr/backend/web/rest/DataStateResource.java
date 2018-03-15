package com.hgicreate.rno.xdr.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hgicreate.rno.xdr.backend.domain.DataState;

import com.hgicreate.rno.xdr.backend.repository.DataStateRepository;
import com.hgicreate.rno.xdr.backend.repository.search.DataStateSearchRepository;
import com.hgicreate.rno.xdr.backend.service.DataStateService;
import com.hgicreate.rno.xdr.backend.web.rest.cond.DataStateCond;
import com.hgicreate.rno.xdr.backend.web.rest.util.HeaderUtil;
import com.hgicreate.rno.xdr.backend.web.rest.util.PaginationUtil;
import com.hgicreate.rno.xdr.backend.service.dto.DataStateDTO;
import com.hgicreate.rno.xdr.backend.service.mapper.DataStateMapper;
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

/**
 * REST controller for managing DataState.
 */
@RestController
@RequestMapping("/api")
public class DataStateResource {

    private final Logger log = LoggerFactory.getLogger(DataStateResource.class);

    private static final String ENTITY_NAME = "dataState";

    private final DataStateRepository dataStateRepository;

    private final DataStateMapper dataStateMapper;

    private final DataStateSearchRepository dataStateSearchRepository;

    private final DataStateService dataStateService;

    public DataStateResource(DataStateRepository dataStateRepository, DataStateMapper dataStateMapper, DataStateSearchRepository dataStateSearchRepository, DataStateService dataStateService) {
        this.dataStateRepository = dataStateRepository;
        this.dataStateMapper = dataStateMapper;
        this.dataStateSearchRepository = dataStateSearchRepository;
        this.dataStateService = dataStateService;
    }

    /**
     * POST  /data-states : Create a new dataState.
     *
     * @param dataStateDTO the dataStateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dataStateDTO, or with status 400 (Bad Request) if the dataState has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/data-states")
    @Timed
    public ResponseEntity<DataStateDTO> createDataState(@RequestBody DataStateDTO dataStateDTO) throws URISyntaxException {
        log.debug("REST request to save DataState : {}", dataStateDTO);
        if (dataStateDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new dataState cannot already have an ID")).body(null);
        }
        DataState dataState = dataStateMapper.toEntity(dataStateDTO);
        dataState = dataStateRepository.save(dataState);
        DataStateDTO result = dataStateMapper.toDto(dataState);
        dataStateSearchRepository.save(dataState);
        return ResponseEntity.created(new URI("/api/data-states/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /data-states : Updates an existing dataState.
     *
     * @param dataStateDTO the dataStateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dataStateDTO,
     * or with status 400 (Bad Request) if the dataStateDTO is not valid,
     * or with status 500 (Internal Server Error) if the dataStateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/data-states")
    @Timed
    public ResponseEntity<DataStateDTO> updateDataState(@RequestBody DataStateDTO dataStateDTO) throws URISyntaxException {
        log.debug("REST request to update DataState : {}", dataStateDTO);
        if (dataStateDTO.getId() == null) {
            return createDataState(dataStateDTO);
        }
        DataState dataState = dataStateMapper.toEntity(dataStateDTO);
        dataState = dataStateRepository.save(dataState);
        DataStateDTO result = dataStateMapper.toDto(dataState);
        dataStateSearchRepository.save(dataState);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dataStateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /data-states : get all the dataStates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dataStates in body
     */
    @GetMapping("/data-states")
    @Timed
    public ResponseEntity<List<DataStateDTO>> getAllDataStates(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DataStates");
        Page<DataState> page = dataStateRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/data-states");
        return new ResponseEntity<>(dataStateMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /data-states/:id : get the "id" dataState.
     *
     * @param id the id of the dataStateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dataStateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/data-states/{id}")
    @Timed
    public ResponseEntity<DataStateDTO> getDataState(@PathVariable Long id) {
        log.debug("REST request to get DataState : {}", id);
        DataState dataState = dataStateRepository.findOne(id);
        DataStateDTO dataStateDTO = dataStateMapper.toDto(dataState);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dataStateDTO));
    }

    /**
     * DELETE  /data-states/:id : delete the "id" dataState.
     *
     * @param id the id of the dataStateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/data-states/{id}")
    @Timed
    public ResponseEntity<Void> deleteDataState(@PathVariable Long id) {
        log.debug("REST request to delete DataState : {}", id);
        dataStateRepository.delete(id);
        dataStateSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/data-states?query=:query : search for the dataState corresponding
     * to the query.
     *
     * @param cond the query of the dataState search
     * @return the result of the search
     */
    @GetMapping("/_search/data-states")
    @Timed
    public ResponseEntity<List<DataStateDTO>> searchByCond(@ApiParam DataStateCond cond) {
        log.debug("REST request to get a page of exception-states: cond = {}", cond);
        List<DataStateDTO> list = dataStateService.searchByCond(cond);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(list));
    }

    /**
     * SEARCH  /_search/data-states?query=:query : search for the dataState corresponding
     * to the query.
     *
     * @param cond the query of the dataState search
     * @return the result of the search
     */
    @GetMapping("/_search/data-analysis")
    @Timed
    public ResponseEntity<List<DataStateDTO>> analysisByCond(@ApiParam DataStateCond cond) {
        log.debug("REST request to get a page of exception-analysis: cond = {}", cond);
        List<DataStateDTO> list = dataStateService.analysisByCond(cond);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(list));
    }

}
