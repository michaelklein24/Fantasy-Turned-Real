import { Injectable } from '@angular/core';
import { timestamp } from 'rxjs';

interface CacheEntry<T> {
  timestamp: number,
  data: T
}

@Injectable({
  providedIn: 'root',
})
export class CacheService {
  private cache: { [key: string]: CacheEntry<any> } = {};
  private readonly cacheTTL = 5 * 60 * 1000; // Default TTL: 5 minutes

  // Set data in cache
  set<T>(key: string, data: T, ttl = this.cacheTTL): void {
    const cacheEntry: CacheEntry<T> = {
      timestamp: Date.now(),
      data: data
    }
    this.cache[key] = cacheEntry;
  }

  get<T>(cacheName: string): T | null {
    const cacheEntry: CacheEntry<T> = this.cache[cacheName];  
    if (cacheEntry) {
      const age = Date.now() - cacheEntry.timestamp;  
      if (age < this.cacheTTL) {
        return cacheEntry.data as T;
      }
    }
    return null;
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
