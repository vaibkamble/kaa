/*
 * Copyright 2014 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kaaproject.kaa.server.common.dao.cassandra.model;

import static org.kaaproject.kaa.server.common.dao.cassandra.CassandraDaoUtil.getStringId;
import static org.kaaproject.kaa.server.common.dao.cassandra.CassandraDaoUtil.getUuidId;
import static org.kaaproject.kaa.server.common.dao.cassandra.model.CassandraModelConstants.ENDPOINT_USER_ACCESS_TOKEN_PROPERTY;
import static org.kaaproject.kaa.server.common.dao.cassandra.model.CassandraModelConstants.ENDPOINT_USER_COLUMN_FAMILY_NAME;
import static org.kaaproject.kaa.server.common.dao.cassandra.model.CassandraModelConstants.ENDPOINT_USER_ENDPOINT_IDS_PROPERTY;
import static org.kaaproject.kaa.server.common.dao.cassandra.model.CassandraModelConstants.ENDPOINT_USER_EXTERNAL_ID_PROPERTY;
import static org.kaaproject.kaa.server.common.dao.cassandra.model.CassandraModelConstants.ENDPOINT_USER_TENANT_ID_PROPERTY;
import static org.kaaproject.kaa.server.common.dao.cassandra.model.CassandraModelConstants.ENDPOINT_USER_USERNAME_PROPERTY;
import static org.kaaproject.kaa.server.common.dao.cassandra.model.CassandraModelConstants.ENDPOINT_USER_USER_ID_PROPERTY;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import org.kaaproject.kaa.common.dto.EndpointUserDto;
import org.kaaproject.kaa.server.common.dao.model.EndpointUser;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Table(name = ENDPOINT_USER_COLUMN_FAMILY_NAME)
public final class CassandraEndpointUser implements EndpointUser, Serializable {

    @Transient
    private static final long serialVersionUID = 3766947955702551264L;

    @PartitionKey
    @Column(name = ENDPOINT_USER_USER_ID_PROPERTY)
    private UUID id;
    @Column(name = ENDPOINT_USER_USERNAME_PROPERTY)
    private String username;
    @Column(name = ENDPOINT_USER_EXTERNAL_ID_PROPERTY)
    private String externalId;
    @Column(name = ENDPOINT_USER_TENANT_ID_PROPERTY)
    private String tenantId;
    @Column(name = ENDPOINT_USER_ACCESS_TOKEN_PROPERTY)
    private String accessToken;
    @Column(name = ENDPOINT_USER_ENDPOINT_IDS_PROPERTY)
    private List<String> endpointIds;

    public CassandraEndpointUser() {
    }

    public CassandraEndpointUser(EndpointUserDto dto) {
        this.id = getUuidId(dto.getId());
        this.username = dto.getUsername();
        this.externalId = dto.getExternalId();
        this.tenantId = dto.getTenantId();
        this.accessToken = dto.getAccessToken();
        this.endpointIds = dto.getEndpointIds();
    }

    public String getId() {
        return getStringId(id);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<String> getEndpointIds() {
        return endpointIds;
    }

    public void setEndpointIds(List<String> endpointIds) {
        this.endpointIds = endpointIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((externalId == null) ? 0 : externalId.hashCode());
        result = prime * result + ((tenantId == null) ? 0 : tenantId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CassandraEndpointUser other = (CassandraEndpointUser) obj;
        if (externalId == null) {
            if (other.externalId != null) {
                return false;
            }
        } else if (!externalId.equals(other.externalId)) {
            return false;
        }
        if (tenantId == null) {
            if (other.tenantId != null) {
                return false;
            }
        } else if (!tenantId.equals(other.tenantId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EndpointUser [id=" + id + ", username=" + username + ", externalId=" + externalId + ", tenantId=" + tenantId + ", accessToken=" + accessToken
                + ", endpointIds=" + endpointIds + "]";
    }

    @Override
    public EndpointUserDto toDto() {
        EndpointUserDto dto = new EndpointUserDto();
        dto.setId(getStringId(id));
        dto.setUsername(username);
        dto.setExternalId(externalId);
        dto.setTenantId(tenantId);
        dto.setAccessToken(accessToken);
        dto.setEndpointIds(endpointIds);
        return dto;
    }
}