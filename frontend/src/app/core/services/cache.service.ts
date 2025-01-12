import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CacheService {
  private cache: { [key: string]: { timestamp: number; data: any } } = {};
  private readonly cacheTTL = 5 * 60 * 1000; // Default TTL: 5 minutes

  // Set data in cache
  set<T>(key: string, data: T, ttl = this.cacheTTL): void {
    this.cache[key] = { timestamp: Date.now(), data };
  }

  // Get data from cache if valid
  get<T>(cacheName: string): T | null {
    const cacheEntry = this.cache[cacheName];
    if (cacheEntry && Date.now() - cacheEntry.timestamp < this.cacheTTL) {
      return cacheEntry.data as T;
    }
    return null; // Cache expired or not found
  } 

  clear(cacheName?: string): void {
    if (cacheName) {
      delete this.cache[cacheName];
    } else {
      this.cache = {}; // Clear all caches
    }
  }

  isValid(cacheName: string): boolean {
    const cacheEntry = this.cache[cacheName];
    return !!(cacheEntry && Date.now() - cacheEntry.timestamp < this.cacheTTL);
  }
}
