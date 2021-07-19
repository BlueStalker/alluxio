/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.client.file.cache;

import alluxio.client.file.CacheContext;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;

/**
 * A class identifies a single cached page.
 */
@ThreadSafe
public class PageId {
  private final String mFileId;
  private final long mPageIndex;
  private final Optional<CacheContext> mCacheContext;

  /**
   * @param fileId file Id
   * @param pageIndex index of the page in file
   */
  public PageId(String fileId, long pageIndex) {
    this(fileId, pageIndex, Optional.empty());
  }

  /**
   * @param fileId file Id
   * @param pageIndex index of the page in file
   * @param cacheContext cache context of the page
   */
  public PageId(String fileId, long pageIndex, Optional<CacheContext> cacheContext) {
    mFileId = fileId;
    mPageIndex = pageIndex;
    mCacheContext = cacheContext;
  }

  /**
   * @return file id
   */
  public String getFileId() {
    return mFileId;
  }

  /**
   * @return index of the page in file
   */
  public long getPageIndex() {
    return mPageIndex;
  }

  /**
   * @return cache context of the page
   */
  public Optional<CacheContext> getCacheContext() {
    return mCacheContext;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mFileId, mPageIndex, mCacheContext);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof PageId)) {
      return false;
    }
    PageId that = (PageId) obj;
    return mFileId.equals(that.mFileId)
        && mPageIndex == that.mPageIndex
        && mCacheContext.equals(that.mCacheContext);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("FileId", mFileId)
        .add("PageIndex", mPageIndex)
        .add("CacheContext", mCacheContext)
        .toString();
  }
}
